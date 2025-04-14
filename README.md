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
![Página Inicial do SAP BTP](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(1).png)
![Página Inicial do SAP BTP](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(2).png)

### Dashboard do SAP BTP
![Dashboard SAP BTP](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(3).png)
![Dashboard SAP BTP](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(4).png)

### Painel de Controle (Grid View)
![Painel de Controle BTP](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(5).png)
![Painel de Controle BTP](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(6).png)

### Aplicações e Serviços
![Aplicações e Serviços](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(7).png)
![Aplicações e Serviços](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(8).png)

### Interface de Incidentes
![Interface de Incidentes](https://raw.githubusercontent.com/cabralbrcwb/incident-management/89abb98e4bcf0cf13a6fdf7d93ee5077fc138c2b/screenshots/home_btp_01_2025%20(9).png)

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
       ▼                      ▼                 └────────────────
