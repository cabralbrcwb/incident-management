# Aplicação de Gerenciamento de Incidentes

![SAP CAP](https://img.shields.io/badge/SAP-CAP-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![SAP BTP](https://img.shields.io/badge/SAP-BTP-green)
![Cloud Foundry](https://img.shields.io/badge/Cloud-Foundry-lightblue)

## 📋 Visão Geral

Uma aplicação robusta de gerenciamento de incidentes construída com SAP Cloud Application Programming Model (CAP) utilizando Java. Esta solução fornece uma plataforma completa para rastreamento, gerenciamento e resolução de incidentes, totalmente integrada com o ecossistema SAP e implantada na SAP Business Technology Platform (BTP).



## 🚀 Principais Recursos

- Gerenciamento completo do ciclo de vida de incidentes
- Rastreamento e atribuição de status
- Controle de acesso baseado em funções (suporte/admin)
- Gerenciamento de dados de clientes
- Rastreamento de histórico de conversas
- Interface SAP Fiori Elements responsiva
- Implantação simplificada usando Multi-Target Application (MTA)
- Integração com API ViaCEP para consulta automática de endereços

## 🛠️ Stack Tecnológica

- **Backend**: SAP Cloud Application Programming Model (CAP) com Java
- **Runtime**: SAP BTP Cloud Foundry
- **Banco de Dados**: SAP HANA Cloud
- **Frontend**: SAP Fiori Elements
- **Portal**: SAP Build Work Zone
- **Protocolo API**: OData
- **Autenticação**: SAP Cloud Identity Service

## 🏗️ Arquitetura

A aplicação segue uma arquitetura multicamada:

```
┌─────────────────────────────────────────────────────────────────┐
│                         Usuário Final                           │
└───────────────────────────────┬─────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────────┐
│                   SAP Cloud Identity Service                    │
└───────────────────────────────┬─────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────────┐
│                     SAP BTP (Cloud Foundry)                     │
│                                                                 │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐  │
│  │  UI Layer       │  │  Service Layer  │  │  Data Layer     │  │
│  │  Fiori Elements │──┼─▶ CAP Services  │──┼─▶ HANA Cloud DB │  │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘  │
│                                                                 │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐  │
│  │ HTML5 App Repo  │  │ Authorization & │  │ Destination     │  │
│  │ Build Work Zone │  │ Trust Management│  │ Service         │  │
│  └─────────────────┘  └─────────────────┘  └────────┬────────┘  │
└──────────────────────────────────────────────────────┼──────────┘
                                                       │
                                                       ▼
┌─────────────────────────────────────────────────────────────────┐
│                       SAP S/4HANA Cloud                         │
└─────────────────────────────────────────────────────────────────┘
```

## 📸 Capturas de Tela

### Página Inicial do SAP BTP
![Página Inicial do SAP BTP](https://github.com/cabralbrcwb/incident-management/raw/main/screenshots/home_btp_01.png)

### Instâncias e Subscrições
![Instâncias e Subscrições](https://github.com/cabralbrcwb/incident-management/raw/main/screenshots/instances_subscriptions.png)

### Painel de Controle SAP BTP (Grid View)
![Painel de Controle SAP BTP 1](https://github.com/cabralbrcwb/incident-management/raw/main/screenshots/grid_btp_01.png)
![Painel de Controle SAP BTP 2](https://github.com/cabralbrcwb/incident-management/raw/main/screenshots/grid_btp_02.png)

### Aplicações de Desenvolvimento
![Aplicações de Desenvolvimento](https://github.com/cabralbrcwb/incident-management/raw/main/screenshots/dev_applications.png)

### Processo de Implantação MTA
![Processo de Implantação MTA](https://github.com/cabralbrcwb/incident-management/raw/main/screenshots/deploy_mta.png)

### Exemplo da Interface de Incidentes
![Lista de Incidentes](https://github.com/cabralbrcwb/incident-management/raw/main/screenshots/incidents-list.png)
![Detalhes do Incidente](https://github.com/cabralbrcwb/incident-management/raw/main/screenshots/incident-details.png)

## 📁 Estrutura do Projeto

```
incident-management/
├── app/                  # Artefatos de UI
├── db/                   # Modelos e definições de dados
│   ├── data/             # Arquivos CSV com dados de exemplo
│   └── schema.cds        # Modelos de dados principais
├── srv/                  # Definições de serviço e lógica de negócios
│   └── services.cds      # Definições de serviço
├── mta.yaml              # Descritor Multi-Target Application
├── package.json          # Configuração do pacote Node.js
└── pom.xml               # Configuração do projeto Maven
```

## 🔄 Modelo de Dados

O aplicativo gerencia as seguintes entidades principais:

```
┌───────────────┐      ┌────────────────┐      ┌────────────────┐
│   Customers   │◄─────┤    Incidents   │─────▶│     Status     │
├───────────────┤      ├────────────────┤      ├────────────────┤
│ ID            │      │ ID             │      │ code           │
│ firstName     │      │ customer_ID    │      │ description    │
│ lastName      │      │ title          │      │ criticality    │
│ email         │      │ urgency_code   │      └────────────────┘
│ phone         │      │ status_code    │      ┌────────────────┐
│ ...           │      │ ...            │─────▶│    Urgency     │
└───────────────┘      └────────────────┘      ├────────────────┤
       │                      │                 │ code           │
       │                      │                 │ description    │
       ▼                      ▼                 └────────────────┘
┌───────────────┐      ┌────────────────┐
│   Addresses   │      │  Conversation  │
├───────────────┤      ├────────────────┤
│ ID            │      │ ID             │
│ customer_ID   │      │ incident_ID    │
│ city          │      │ timestamp      │
│ postCode      │      │ author         │
│ streetAddress │      │ message        │
└───────────────┘      └────────────────┘
       ▲
       │
       │
┌───────────────┐
│  ViaCEP API   │
├───────────────┤
│ cep           │
│ logradouro    │
│ bairro        │
│ localidade    │
│ uf            │
└───────────────┘
```

### Modelo CDS da Integração ViaCEP

```cds
namespace com.mycompany.cepsvc;

// Define um tipo estruturado Endereco,
// representando o payload do ViaCEP
type Endereco {
    cep         : String; 
    logradouro  : String;
    complemento : String;
    bairro      : String;
    localidade  : String;
    uf          : String;
    ibge        : String;
    ddd         : String;
    siafi       : String;
}

// Servico OData: CepService
service CepService {
    // Action nao-vinculada: recebe um CEP, retorna um Endereco
    action buscarEndereco(cep: String) returns Endereco;
}
```

## 🔒 Segurança

A aplicação implementa autorização granular através do SAP Authorization and Trust Management Service, com definições específicas de funções:

- **support**: Acesso para visualizar e processar incidentes
- **admin**: Acesso para gerenciar configurações e realizar tarefas administrativas

## 🚀 Implantação

O projeto utiliza o conceito de Multi-Target Application (MTA) da SAP para implantação em ambientes Cloud Foundry:

```bash
# Construir o arquivo MTA
mbt build

# Implantar no Cloud Foundry
cf deploy mta_archives/incident-management_1.0.0.mtar
```

Fluxo de implantação:
```
┌───────────┐     ┌───────────┐     ┌───────────┐     ┌───────────┐
│  Código   │────▶│    MBT    │────▶│   MTAR    │────▶│  Deploy   │
│  Fonte    │     │   Build   │     │  Arquivo  │     │    CF     │
└───────────┘     └───────────┘     └───────────┘     └───────────┘
```

![Processo de Implantação](https://github.com/seuusuario/incident-management/raw/main/screenshots/deployment-process.png)

## 🏃‍♂️ Executando Localmente

### Pré-requisitos

- Node.js (v16 ou posterior)
- SAP Cloud Application Programming Model (CAP) CLI
- JDK 11 ou superior
- Maven
- SAP Business Application Studio ou VS Code com extensões apropriadas

### Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/incident-management.git
   cd incident-management
   ```

2. Instale as dependências:
   ```bash
   npm install
   ```

3. Execute localmente:
   ```bash
   cds watch
   ```

### Desenvolvimento

Para desenvolvimento Java:
```bash
mvn clean install
mvn spring-boot:run
```

Fluxo de desenvolvimento:
```
┌───────────┐     ┌───────────┐     ┌───────────────────┐
│ git clone │────▶│    npm    │────▶│    cds watch      │
│           │     │  install  │     │      ou           │
└───────────┘     └───────────┘     │ mvn spring-boot:run│
                                    └───────────────────┘
```

![Ambiente de Desenvolvimento](https://github.com/seuusuario/incident-management/raw/main/screenshots/development-environment.png)

## 📄 Documentação da API

Serviços OData estão disponíveis em:
- `/odata/v4/ProcessorService` - Para pessoal de suporte
- `/odata/v4/AdminService` - Para funções administrativas

## 🔗 Integração

A aplicação pode ser integrada com:
- SAP S/4HANA Cloud
- SAP Build Work Zone
- Sistemas externos de notificação
- ViaCEP API para consulta de endereços baseada em CEP

### Integração com ViaCEP

O sistema utiliza a API pública ViaCEP para automatizar a consulta de endereços no cadastro de clientes. Esta integração permite:

1. **Preenchimento automático de endereços**: Ao informar um CEP no cadastro de cliente, o sistema consulta a API ViaCEP e preenche automaticamente os campos de endereço.

2. **Redução de erros de digitação**: Elimina a necessidade de digitação manual de endereços, diminuindo erros e inconsistências.

3. **Padronização de dados**: Garante que os dados de endereço sigam o padrão oficial dos Correios.

#### Implementação Técnica

A integração é feita através de um serviço OData que consome a API REST do ViaCEP:

```java
// CepServiceHandler.java
@Component
public class CepServiceHandler implements EventHandler {

    @On(event = BuscarEnderecoContext.CDS_NAME)
    public void onBuscarEndereco(BuscarEnderecoContext context) {
        String cep = context.getCep();
        // Validação do formato do CEP
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new ServiceException(ErrorStatuses.BAD_REQUEST,
                "CEP inválido. Informe 8 dígitos numéricos.");
        }

        // Chamada à API ViaCEP
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            EnderecoResponse apiResponse = restTemplate.getForObject(url, EnderecoResponse.class);
            // Conversão e retorno do resultado
            context.setResult(EnderecoMapper.map(apiResponse));
        } catch (Exception e) {
            // Tratamento de erros
            throw new ServiceException(ErrorStatuses.BAD_GATEWAY,
                "Erro ao consultar ViaCEP: " + e.getMessage(), e);
        }
    }
}

## 📝 Licença

Este projeto está licenciado sob a Licença MIT - consulte o arquivo LICENSE para obter detalhes.

## 📊 Estatísticas e Monitoramento

O aplicativo oferece integração com ferramentas de monitoramento do SAP BTP, permitindo:

- Análise em tempo real da utilização da aplicação
- Monitoramento de desempenho e tempos de resposta
- Acompanhamento de erros e exceções
- Visualização de métricas de uso e disponibilidade

### Monitoramento via SAP BTP Cockpit

A interface do SAP BTP Cockpit oferece painéis detalhados para monitorar:

- Número de incidentes abertos/fechados por período
- Tempo médio de resolução de incidentes
- Distribuição de incidentes por urgência e status
- Utilização de recursos da plataforma (memória, CPU, requisições)


Este projeto demonstra a implementação de uma solução robusta de gerenciamento de incidentes aproveitando todo o potencial do ecossistema SAP Cloud, fornecendo uma base sólida que pode ser estendida para atender a necessidades específicas de negócios.

