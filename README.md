 🍴NutriExpress - API REST Delivery

API RESTful em Spring Boot desenvolvida para a gestão de delivery de comida saudável. O sistema implementa uma arquitetura em camadas completa (Controller -> Service -> Repository -> Banco de Dados) com persistência via Spring Data JPA e tratamento global de exceções.

🛠 Tecnologias
Linguagem: Java 21
Framework: Spring Boot 4.1 (Spring Web, Spring Data JPA, Validation)
Banco de Dados: H2 (em memória, para desenvolvimento/testes) — pronto para PostgreSQL
Gerenciador de Dependências: Maven
Testes: Postman
📁 Estrutura de Pastas
src/main/java/com/delivery/delivery/
├── controller/    # Endpoints REST (@RestController)
├── dto/           # Java Records (Request / Response)
├── exception/     # Handler global e exceções personalizadas
├── model/         # Entidades JPA (@Entity)
├── repository/    # Interfaces Spring Data JPA
└── service/       # Regras de negócio e conversões (toEntity / toDTO)
▶️ Como Rodar
bash
git clone https://github.com/Fe403/NutriExpress.git
cd NutriExpress
./mvnw spring-boot:run

A API sobe em http://localhost:8080.

Sobre o banco de dados: o projeto roda com H2 (memória) por padrão, sem precisar instalar nada. A entidade Prato já está mapeada como @Entity e o PratoRepository já estende JpaRepository, então migrar para PostgreSQL é só trocar 3 linhas do application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/delivery
spring.datasource.username=postgres
spring.datasource.password=postgres

Nenhuma camada (Controller, Service, Repository) precisa mudar — essa é a vantagem da arquitetura em camadas.

📡 Endpoints
Verbo	Rota	Descrição	Status
GET	/pratos	Lista todos os pratos	200
GET	/pratos/{id}	Busca um prato pelo ID	200 / 404
GET	/pratos?categoria=vegano	Filtra pratos por categoria	200
POST	/pratos	Cria um novo prato	201
PUT	/pratos/{id}	Atualiza um prato existente	200 / 404
DELETE	/pratos/{id}	Remove um prato	204 / 404
PATCH	/pratos/{id}/valor	Atualiza somente o valor 🎁	200 / 404
GET	/pratos/calorias?max=500	Filtra por calorias máximas 🎁	200

(🎁 = desafio extra)

📦 Exemplo de Requisição (POST/PUT)
json
{
  "nome": "Bowl de Quinoa",
  "descricao": "Quinoa, legumes grelhados e molho de tahine",
  "valor": 28.90,
  "categoria": "vegano",
  "calorias": 420,
  "quantidade": 350.0,
  "unidadeMedida": "g"
}
⚙️ Regra de Negócio

Não é permitido cadastrar dois pratos com o mesmo nome (validado em PratoService.criar(), ignorando maiúsculas/minúsculas).

🚨 Tratamento de Erros

Um GlobalExceptionHandler (@RestControllerAdvice) centraliza:

Erros de validação (@Valid) → 400 Bad Request com mapa de campos inválidos
Prato não encontrado → 404 Not Found
Nome duplicado → 400 Bad Request
✅ Testes

Endpoints testados manualmente via Postman, cobrindo criação, listagem, busca por ID, filtro por categoria, atualização, remoção e validações de erro (evidências em anexo).
