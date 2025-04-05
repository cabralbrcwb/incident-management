package customer.incident_management.util;

import cds.gen.Endereco;

import customer.incident_management.handler.EnderecoResponse;

/**
 * Classe utilitária para converter EnderecoResponse (POJO) 
 * em Endereco (tipo gerado pelo CAP).
 */
public class EnderecoMapper {

    private EnderecoMapper() {}

    /**
     * Converte um EnderecoResponse (dados retornados do ViaCEP)
     * em uma instância do tipo Endereco (definido no .cds).
     */
    public static Endereco mapToEndereco(EnderecoResponse resp) {
        Endereco e = Endereco.create();

        // Mapeia campos manualmente
        e.setCep(resp.getCep());
        e.setLogradouro(resp.getLogradouro());
        e.setComplemento(resp.getComplemento());
        e.setBairro(resp.getBairro());
        e.setLocalidade(resp.getLocalidade());
        e.setUf(resp.getUf());
        e.setIbge(resp.getIbge());
        e.setDdd(resp.getDdd());
        e.setSiafi(resp.getSiafi());
        // Se tiver 'gia' no seu .cds e no EnderecoResponse, inclua aqui.

        return e;
    }
}
