# 🏥 Doctor API - Sistema de Agendamento Médico

Esta é uma API RESTful robusta desenvolvida em Java e Spring Boot para o gerenciamento de um consultório médico. O sistema abrange o cadastro de médicos e pacientes, e o agendamento inteligente de consultas. O foco é na **integridade dos dados**, **segurança** (via JWT), e aplicação de **regras de negócio** para agendamentos válidos.

## ✨ Funcionalidades e Escopo da Aplicação

* **Gerenciamento Completo (CRUD):**
    * Cadastro e manutenção de **Médicos** (CRUD).
    * Cadastro e manutenção de **Pacientes** (CRUD).
* **Agendamento Inteligente de Consultas:**
    * Criação de consultas baseada na **especialidade** do médico.
    * Seleção automática de um médico disponível caso não seja especificado.
* **Segurança:** Autenticação e Autorização robusta utilizando **JWT (JSON Web Token)**.

## 🛡️ Regras de Negócio e Validações

O coração desta API reside na camada de serviço, que impõe validações críticas no agendamento para garantir a qualidade do serviço.

| Regra de Negócio | Descrição |
| :--- | :--- |
| **Horário Comercial** | As consultas **não podem** ser agendadas fora do horário de expediente (ex: antes das 7h ou após as 18h). |
| **Fins de Semana** | As consultas **não podem** ser marcadas em sábados ou domingos. |
| **Antecedência Mínima** | O agendamento deve ser feito com pelo menos 30 minutos de antecedência. |
| **Disponibilidade do Paciente** | Um paciente **não pode** ter duas consultas no mesmo dia. |
| **Disponibilidade do Médico** | Um médico **não pode** ter duas consultas no mesmo horário. |
| **Escolha Automática de Médico** | O sistema deve selecionar o **primeiro médico disponível** que atenda à especialidade solicitada. |

## 🛠️ Tecnologias e Dependências

| Categoria | Tecnologia | Uso |
| :--- | :--- | :--- |
| **Linguagem/Framework** | Java, Spring Boot 3 | Desenvolvimento Backend da API. |
| **Persistência** | Spring Data JPA / Hibernate | Gerenciamento e mapeamento Objeto-Relacional. |
| **Banco de Dados** | **MySQL** | Banco de dados relacional principal. |
| **Documentação** | **Swagger / OpenAPI** | Documentação interativa e *testing* dos *endpoints*. |
| **Segurança** | Spring Security, **JWT (via Auth0)** | Implementação de autenticação *stateless*. |
| **Testes** | JUnit 5, **Mockito** | Testes Unitários, de Lógica e simulação de dependências. |
| **Auxílio Código** | Lombok | Redução de *boilerplate* (getters, setters, construtores). |

## 🧪 Estratégia de Testes

A cobertura de testes é focada em garantir a estabilidade e o comportamento esperado das regras de negócio.

* **Testes de Repository (Integração):** Validação da persistência de dados no banco (simulado ou H2).
* **Testes de Lógica de Negócio (`@Service`):**
    * Teste crucial para a funcionalidade de **seleção automática de médico disponível**, garantindo que as regras de disponibilidade e especialidade sejam rigorosamente aplicadas.
* **Testes de Controller (`@RestController`):**
    * Teste de integração do *endpoint* `GET /doctor/{id}` para garantir a correta resposta em diferentes cenários (sucesso, not found, etc.).

## 🔐 Autenticação e Acesso à API

Para acessar os *endpoints* protegidos (a maioria dos CRUDs e agendamento), é necessário um *token* JWT.

1.  **Obter Token:**
    * **Endpoint:** `POST /login`
    * **Corpo da Requisição (JSON):** Envie as credenciais de um usuário cadastrado.
    * **Resposta:** O token JWT é retornado no corpo da resposta.
2.  **Usar Token:**
    * Inclua o *token* no cabeçalho de todas as requisições protegidas:
        ```
        Authorization: Bearer <SEU_JWT_AQUI>
        ```

## ⚙️ Como Configurar e Executar a Aplicação

### Pré-requisitos

* Java Development Kit (JDK) 17 ou superior
* MySQL Server (versão 8.x recomendada)
* Maven
* **Cliente HTTP:** **Postman, Insomnia** ou similar (necessário para interagir com a API, especialmente para login e endpoints protegidos).

### 1. Configuração do MySQL

1.  Crie um novo banco de dados no seu servidor MySQL (ex: `consultorio_db`).
2.  Atualize o arquivo de configuração (ex: `application.properties` ou `.yml`) com suas credenciais:

    ```properties
    # Exemplo de configuração no application.properties
    spring.datasource.url=jdbc:mysql://localhost:3306/consultorio_db?useTimezone=true&serverTimezone=UTC
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql
    spring.jpa.hibernate.ddl-auto=update
    ```

### 2. Execução

1.  **Clone o repositório:**
    ```bash
    git clone [LINK_DO_SEU_REPOSITORIO]
    cd nome-do-projeto
    ```
2.  **Compile o projeto:**
    ```bash
    ./mvnw clean package
    ```
3.  **Execute a aplicação:**
    ```bash
    java -jar target/nome-do-arquivo.jar
    ```

### 🌐 Acessando a Documentação (Swagger UI)

Após a execução da API, a documentação interativa estará disponível.

* **URL:** `http://localhost:8080/swagger-ui.html`

## 📌 Próximos Passos e Melhorias

* **Containers:** Criar *Dockerfile* e **Docker Compose** para inicializar a API e o MySQL com um único comando, simplificando o *setup* para novos desenvolvedores.
* **Mensageria Assíncrona:** Implementar **Kafka** ou **RabbitMQ** para processar notificações (e-mail, SMS) de agendamento de forma assíncrona.
