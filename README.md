# 🛒 Ecommerce API

API REST de um e-commerce com gerenciamento de **produtos** e **categorias**, documentada com **Swagger/OpenAPI** e coberta por **testes automatizados nas três camadas** (repository, service e controller). O projeto aplica validações declarativas, tratamento global de exceções com detalhamento por campo e perfis de ambiente.

> 📚 **Status:** projeto de estudos concluído — consolidação das práticas de API REST com Spring Boot somadas à cultura de testes com JUnit 5 e Mockito.

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3** (Web, Data JPA, Validation, DevTools)
- **H2 Database** — perfil `test`
- **MySQL 8** — perfil `dev`
- **springdoc-openapi** — documentação com Swagger UI
- **JUnit 5 + Mockito + AssertJ** — testes automatizados
- **Maven**

## 🏗️ Estrutura do projeto

```
src/main/java/com/api/ecommerce/
├── controller/    # Endpoints REST (Produto e Categoria)
├── dtos/          # DTOs de entrada/saída com Bean Validation
├── exceptions/    # Handler global + payloads de erro padronizados
├── models/        # Entidades JPA (Produto N—1 Categoria)
├── repositories/  # Interfaces JPA com query methods
└── service/       # Regras de negócio
```

## 📌 Endpoints

### Produtos — `/produto`

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/produto` | Lista todos os produtos |
| `GET` | `/produto/{id}` | Busca produto por id |
| `GET` | `/produto/categoria/{id}` | Lista produtos de uma categoria |
| `POST` | `/produto` | Cadastra produto (com validação) |
| `PUT` | `/produto/{id}` | Atualiza produto |
| `DELETE` | `/produto/{id}` | Remove produto (`204`) |

### Categorias — `/categoria`

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/categoria` | Lista todas as categorias |
| `GET` | `/categoria/{id}` | Busca categoria por id |
| `POST` | `/categoria` | Cadastra categoria |
| `PUT` | `/categoria/{id}` | Atualiza categoria |
| `DELETE` | `/categoria/{id}` | Remove categoria |

A documentação interativa fica no **Swagger UI**, com cada operação descrita via `@Operation`:

```
http://localhost:8080/swagger-ui.html
```

## ⚠️ Tratamento de erros

Handler global (`@RestControllerAdvice`) com payload de erro padronizado (`StandardError`: timestamp, status, mensagem e path):

| Exceção | Status | Detalhe |
|---------|--------|---------|
| `ObjectNotFoundException` | `404` | Recurso não encontrado |
| `IllegalArgumentException` | `400` | Argumento inválido |
| `DataIntegrityViolationException` | `400` | Violação de integridade (ex.: FK) |
| `MethodArgumentNotValidException` | `400` | Falha de validação, com **lista de erros por campo** (`ValidationError` + `FieldMessage`) |

Exemplo de resposta de validação:

```json
{
  "timestamp": "2025-01-15T10:30:00",
  "status": 400,
  "error": "Erro na validação do campo.",
  "path": "/produto",
  "errors": [
    { "fieldName": "nome", "message": "não deve estar em branco" },
    { "fieldName": "preco", "message": "não deve ser nulo" }
  ]
}
```

## 🧪 Testes

O projeto tem testes automatizados em três níveis, com nomenclatura BDD (`Dado_Quando_Então`):

| Camada | Estratégia |
|--------|------------|
| **Repository** | `@DataJpaTest` com H2 e AssertJ — testa query methods reais contra o banco em memória |
| **Service** | Teste unitário puro com `@Mock`/`@InjectMocks` — regras de negócio isoladas do banco, com `verify` de interações |
| **Controller** | Teste unitário do controller com service mockado — valida status HTTP e payloads |

```bash
./mvnw test
```

## ⚙️ Perfis de ambiente

| Perfil | Banco | Uso |
|--------|-------|-----|
| `test` (padrão) | H2 em memória, console em `/h2-console` | Desenvolvimento e testes |
| `dev` | MySQL (`createDatabaseIfNotExist=true`) | Banco local persistente |

O banco é populado automaticamente com categorias e produtos de exemplo via `schema.sql` e `data.sql`.

## 🚀 Como rodar

### Pré-requisitos

- Java 17+
- (Opcional) MySQL 8 para o perfil `dev`

### Rodando com H2 (zero configuração)

```bash
git clone https://github.com/luanmvcosta0/EcommerceAPI.git
cd EcommerceAPI
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080` já com dados de exemplo. Console do H2 em `/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, usuário `sa`, sem senha).

### Exemplo de requisição

```http
POST /produto
Content-Type: application/json

{
  "nome": "Smartphone",
  "descricao": "iPhone 13 Pro Max",
  "preco": 6140.99,
  "quantidadeEstoque": 7,
  "categoria": { "id": 1 }
}
```

## 🎯 O que eu pratiquei nesse projeto

- CRUD completo em camadas com DTOs e **Bean Validation** (`@Valid`)
- **Tratamento global de exceções** com payload padronizado e erros detalhados por campo
- Documentação de API com **Swagger/OpenAPI**
- **Testes em três camadas**: `@DataJpaTest`, unitários com Mockito (`when`, `verify`, `thenAnswer`) e testes de controller
- Seed de banco com `schema.sql` e `data.sql`
- Perfis de ambiente (test/dev) com H2 e MySQL

---

Feito por [Luan Costa](https://github.com/luanmvcosta0) 👋
