package customer.incident_management.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.cepservice.CepService_;
import customer.incident_management.util.EnderecoMapper;
import cds.gen.Endereco;
import cds.gen.cepservice.BuscarEnderecoContext;

@Component
@ServiceName(CepService_.CDS_NAME)
public class CepServiceHandler implements EventHandler {

    @On(event = "buscarEndereco")
    public void onBuscarEndereco(BuscarEnderecoContext context) {
        String cep = context.getCep();
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new ServiceException(ErrorStatuses.BAD_REQUEST,
                "CEP inválido. Informe 8 dígitos.");
        }
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            EnderecoResponse resposta = restTemplate.getForObject(url, EnderecoResponse.class);

            if (resposta == null || resposta.getCep() == null) {
                throw new ServiceException(ErrorStatuses.NOT_FOUND,
                    "CEP não encontrado: " + cep);
            }
            Endereco endereco = EnderecoMapper.mapToEndereco(resposta);
            context.setResult(endereco);

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ErrorStatuses.SERVER_ERROR,
                "Falha ao buscar CEP: " + e.getMessage(), e);
        }
    }
}
