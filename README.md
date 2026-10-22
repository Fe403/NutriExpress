# 🍽️ API REST — Delivery de Comida Saudável

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de pratos de um aplicativo de delivery de comida saudável.

O projeto foi desenvolvido seguindo uma arquitetura em camadas:

**Controller → Service → Repository → PostgreSQL**

A aplicação utiliza DTOs para entrada e saída de dados, mantendo a entidade `Prato` separada das respostas HTTP.

## 🚀 Tecnologias utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate/JPA
* PostgreSQL
* Bean Validation
* Maven
* Postman/Insomnia para testes

## 📁 Estrutura do projeto

```text
src/main/java/.../delivery/
├── model/
│   └── Prato.java
├── repository/
│   └── PratoRepository.java
├── dto/
│   ├── PratoRequestDTO.java
│   └── PratoResponseDTO.java
├── service/
│   └── PratoService.java
├── controller/
│   └── PratoController.java
└── exception/
    └── GlobalExceptionHandler.java

src/main/resources/
└── application.properties
```

## 🧱 Arquitetura

### Controller

Responsável por receber as requisições HTTP, validar os dados e chamar o `PratoService`.

O Controller não acessa o banco de dados diretamente e não retorna a entidade `Prato`.

### Service

Responsável pelas regras de negócio da aplicação.

Também realiza a conversão entre:

* `PratoRequestDTO` → `Prato`
* `Prato` → `PratoResponseDTO`

### Repository

Responsável pelo acesso aos dados utilizando `Spring Data JPA` e `JpaRepository`.

### DTO

Os DTOs são utilizados para controlar os dados recebidos e enviados pela API.

* `PratoRequestDTO`: dados para criação e atualização.
* `PratoResponseDTO`: dados retornados pela API.

## 🍴 Modelo Prato

O recurso `Prato` possui os seguintes campos:

| Campo           | Tipo       | Descrição              |
| --------------- | ---------- | ---------------------- |
| `id`            | Long       | Identificador do prato |
| `nome`          | String     | Nome do prato          |
| `descricao`     | String     | Descrição              |
| `valor`         | BigDecimal | Preço do prato         |
| `categoria`     | String     | Categoria do prato     |
| `calorias`      | Integer    | Valor calórico         |
| `quantidade`    | Double     | Quantidade da porção   |
| `unidadeMedida` | String     | `g` ou `ml`            |

Categorias utilizadas:

* `vegano`
* `low carb`
* `fitness`
* `sobremesa saudável`

## 🔗 Endpoints

### Listar todos os pratos

```http
GET /pratos
```

Retorna todos os pratos cadastrados.

**Status:** `200 OK`

---

### Buscar prato por ID

```http
GET /pratos/{id}
```

Busca um prato utilizando seu identificador.

**Status:**

* `200 OK`
* `404 Not Found`

---

### Filtrar por categoria

```http
GET /pratos?categoria=vegano
```

Retorna os pratos pertencentes à categoria informada.

**Status:** `200 OK`

---

### Criar prato

```http
POST /pratos
```

Cria um novo prato.

**Status:** `201 Created`

Exemplo:

```json
{
  "nome": "Bowl Fitness",
  "descricao": "Bowl saudável com frango e vegetais",
  "valor": 25.90,
  "categoria": "fitness",
  "calorias": 450,
  "quantidade": 350,
  "unidadeMedida": "g"
}
```

---

### Atualizar prato

```http
PUT /pratos/{id}
```

Atualiza os dados de um prato existente.

**Status:**

* `200 OK`
* `404 Not Found`

---

### Remover prato

```http
DELETE /pratos/{id}
```

Remove um prato pelo ID.

**Status:**

* `204 No Content`
* `404 Not Found`

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL**.

Configure as informações de conexão no arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/delivery
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Certifique-se de que o PostgreSQL esteja executando antes de iniciar a aplicação.

## ▶️ Como executar

### 1. Clone o projeto

```bash
git clone URL_DO_SEU_REPOSITORIO
```

### 2. Entre na pasta

```bash
cd delivery
```

### 3. Configure o PostgreSQL

Crie o banco de dados:

```text
delivery
```

Depois configure usuário e senha no `application.properties`.

### 4. Execute o projeto

Pelo Maven:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Ou execute a classe principal da aplicação pela sua IDE.

## 🧪 Testes

Os endpoints podem ser testados utilizando:

* Postman
* Insomnia
* cURL

Fluxo recomendado:

1. Criar pratos com `POST /pratos`
2. Listar com `GET /pratos`
3. Buscar por ID
4. Testar filtro por categoria
5. Atualizar com `PUT`
6. Remover com `DELETE`
7. Testar IDs inexistentes
8. Testar validações com dados inválidos

## 📌 Regra de negócio

O projeto possui uma regra de negócio implementada na camada `PratoService`.

Essa regra deve ser documentada no código e aplicada antes da persistência do prato.

## 👨‍💻 Projeto acadêmico

Projeto desenvolvido como atividade prática da disciplina de **Back-End**, com foco em:

* API REST
* Spring Boot
* Arquitetura
