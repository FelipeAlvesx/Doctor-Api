# 🏥 Consultório API - Sistema de Agendamento Médico

Esta é uma API RESTful desenvolvida em Java e Spring Boot para gerenciamento de um consultório médico, abrangendo cadastros de médicos, pacientes e o agendamento inteligente de consultas. O sistema é seguro, seguindo as melhores práticas de autenticação e autorização via JWT (JSON Web Token).

## ✨ Funcionalidades Principais

* **Gerenciamento Completo (CRUD):**
    * Cadastro e manutenção de **Médicos**.
    * Cadastro e manutenção de **Pacientes**.
* **Agendamento Inteligente de Consultas:**
    * Criação de consultas baseada na **especialidade** do médico.
    * **Regras de Negócio Integradas** para garantir a validade do agendamento.
* **Segurança:** Autenticação e Autorização robusta utilizando JWT (via Auth0).

## 🛡️ Regras de Negócio e Validações

O coração desta API é a camada de serviço que impõe validações críticas no agendamento para garantir a qualidade do serviço.

| Regra de Negócio | Descrição |
| :--- | :--- |
| **Horário Comercial** | As consultas **não podem** ser agendadas fora do horário de expediente (ex: antes das 7h ou após as 18h). |
| **Fins de Semana** | As consultas **não podem** ser marcadas em sábados ou domingos. |
| **Antecedência Mínima** | O agendamento deve ser feito com pelo menos 30 minutos de antecedência. |
| **Disponibilidade do Paciente** | Um paciente **não pode** ter duas consultas no mesmo dia. |
| **Disponibilidade do Médico** | Um médico **não pode** ter duas consultas no mesmo horário. |
| **Escolha Automática de Médico** | Se o ID do médico não for fornecido, o sistema seleciona um **médico disponível** que atenda à especialidade solicitada. |

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Framework:** Spring Boot 3
* **Banco de Dados:** Spring Data JPA / Hibernate (H2 para testes e desenvolvimento)
* **Dependências:**
    * **Lombok:** Para reduzir o *boilerplate* de *getters*, *setters* e construtores.
    * **Spring Security:** Para segurança da aplicação.
    * **Auth0:** Utilizado para geração e validação de JWT.
* **Testes:**
    * **JUnit 5:** Framework de teste.
    * **Mockito:** Para criação de *mocks* e simulação de dependências.

## 🧪 Estratégia de Testes

A aplicação possui uma cobertura de testes focada na integridade do banco de dados e na lógica de negócio essencial.

* **Testes de Repository (Unitários/Integração):** Validação da persistência de dados.
* **Testes de Lógica de Negócio (`@Service`):**
    * Foco especial na funcionalidade de **seleção automática de médico disponível**, garantindo que as regras de disponibilidade e especialidade sejam respeitadas.
* **Testes de Controller (`@RestController`):**
    * Teste de integração do *endpoint* `GET /doctor/{id}` para garantir a correta recuperação e serialização dos dados de um médico específico.

## 🔐 Autenticação e Acesso à API

Esta API exige um *token* JWT válido para acessar a maioria dos *endpoints*.

1.  **Obter Token:**
    * **Endpoint:** `POST /login`
    * **Corpo da Requisição (JSON):** `{ "login": "usuario", "senha": "senha" }`
    * **Resposta:** Receberá um *token* JWT.
2.  **Usar Token:**
    * Adicione o *token* no cabeçalho de todas as requisições protegidas:
    ```
    Authorization: Bearer <SEU_JWT_AQUI>
    ```

## ⚙️ Como Executar a Aplicação

### Pré-requisitos

* Java Development Kit (JDK) 17 ou superior
* Maven

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [LINK_DO_SEU_REPOSITORIO]
    cd consultorio-api
    ```
2.  **Compile o projeto (Package):**
    ```bash
    ./mvnw clean package
    ```
3.  **Execute a aplicação:**
    ```bash
    java -jar target/consultorio-api.jar
    ```

A API estará acessível em `http://localhost:8080`.

## 📌 Próximos Passos (Melhorias Futuras)

* **Documentação Interativa:** Implementar **Swagger/OpenAPI** para documentação de *endpoints*.
* **Containers:** Criar *Dockerfile* para facilitar a execução via **Docker** em ambientes de produção.
* **Mensageria:** Implementar comunicação assíncrona (ex: **Kafka**) para notificações de agendamento.
