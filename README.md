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


## 🐳 Executando com Docker (Recomendado)

A maneira mais simples de rodar a aplicação é utilizando **Docker Compose**, que configura automaticamente a API e o banco de dados MySQL.

### Pré-requisitos

* Docker Engine e Docker Compose instalados.

### Passo a Passo

1.  **Clone o repositório:**
<div class="widget code-container remove-before-copy"><div class="code-header non-draggable"><span class="iaf s13 w700 code-language-placeholder">bash</span><div class="code-copy-button"><span class="iaf s13 w500 code-copy-placeholder">Copy</span><img class="code-copy-icon" src="data:image/svg+xml;utf8,%0A%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%3E%0A%20%20%3Cpath%20d%3D%22M10.8%208.63V11.57C10.8%2014.02%209.82%2015%207.37%2015H4.43C1.98%2015%201%2014.02%201%2011.57V8.63C1%206.18%201.98%205.2%204.43%205.2H7.37C9.82%205.2%2010.8%206.18%2010.8%208.63Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%20%20%3Cpath%20d%3D%22M15%204.42999V7.36999C15%209.81999%2014.02%2010.8%2011.57%2010.8H10.8V8.62999C10.8%206.17999%209.81995%205.19999%207.36995%205.19999H5.19995V4.42999C5.19995%201.97999%206.17995%200.999992%208.62995%200.999992H11.57C14.02%200.999992%2015%201.97999%2015%204.42999Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%3C%2Fsvg%3E%0A" /></div></div><pre id="code-s2ed4aiwh" style="color:#111b27;background:#e3eaf2;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;white-space:pre;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none;padding:8px;margin:8px;overflow:auto;width:calc(100% - 8px);border-radius:8px;box-shadow:0px 8px 18px 0px rgba(120, 120, 143, 0.10), 2px 2px 10px 0px rgba(255, 255, 255, 0.30) inset"><code class="language-bash" style="white-space:pre;color:#111b27;background:none;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none"><span>    </span><span class="token" style="color:#7c00aa">git</span><span> clone </span><span class="token" style="color:#111b27">[</span><span>LINK_DO_SEU_REPOSITORIO</span><span class="token" style="color:#111b27">]</span><span>
</span><span>    </span><span class="token" style="color:#005a8e">cd</span><span> nome-do-projeto
</span></code></pre></div>

2.  **Suba os containers:**
    Na raiz do projeto, execute:
<div class="widget code-container remove-before-copy"><div class="code-header non-draggable"><span class="iaf s13 w700 code-language-placeholder">bash</span><div class="code-copy-button"><span class="iaf s13 w500 code-copy-placeholder">Copy</span><img class="code-copy-icon" src="data:image/svg+xml;utf8,%0A%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%3E%0A%20%20%3Cpath%20d%3D%22M10.8%208.63V11.57C10.8%2014.02%209.82%2015%207.37%2015H4.43C1.98%2015%201%2014.02%201%2011.57V8.63C1%206.18%201.98%205.2%204.43%205.2H7.37C9.82%205.2%2010.8%206.18%2010.8%208.63Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%20%20%3Cpath%20d%3D%22M15%204.42999V7.36999C15%209.81999%2014.02%2010.8%2011.57%2010.8H10.8V8.62999C10.8%206.17999%209.81995%205.19999%207.36995%205.19999H5.19995V4.42999C5.19995%201.97999%206.17995%200.999992%208.62995%200.999992H11.57C14.02%200.999992%2015%201.97999%2015%204.42999Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%3C%2Fsvg%3E%0A" /></div></div><pre id="code-vnqbop180" style="color:#111b27;background:#e3eaf2;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;white-space:pre;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none;padding:8px;margin:8px;overflow:auto;width:calc(100% - 8px);border-radius:8px;box-shadow:0px 8px 18px 0px rgba(120, 120, 143, 0.10), 2px 2px 10px 0px rgba(255, 255, 255, 0.30) inset"><code class="language-bash" style="white-space:pre;color:#111b27;background:none;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none"><span>    </span><span class="token" style="color:#7c00aa">docker-compose</span><span> up </span><span class="token" style="color:#005a8e">--build</span><span>
</span></code></pre></div>
    *Este comando irá compilar a aplicação, criar a imagem Docker e iniciar os containers da API e do banco de dados.*

3.  **Acesse a aplicação:**
    * A API estará rodando em: `http://localhost:8080`
    * O banco de dados estará acessível na porta `3306`.

### Comandos Úteis

* **Parar a aplicação:** `docker-compose down`
* **Parar e remover volumes (limpar dados):** `docker-compose down -v`
* **Ver logs:** `docker-compose logs -f`

    

### 🌐 Acessando a Documentação (Swagger UI)

Após a execução da API, a documentação interativa estará disponível.

* **URL:** `http://localhost:8080/swagger-ui.html`

## 📌 Próximos Passos e Melhorias

* **Containers:** Criar *Dockerfile* e **Docker Compose** para inicializar a API e o MySQL com um único comando, simplificando o *setup* para novos desenvolvedores - Feito✅.
* **Mensageria Assíncrona:** Implementar **Kafka** ou **RabbitMQ** para processar notificações (e-mail, SMS) de agendamento de forma assíncrona.
