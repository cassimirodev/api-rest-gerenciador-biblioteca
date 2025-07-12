
# API REST - Gerenciador de Biblioteca

API REST desenvolvida em Java com Spring Boot para gerenciamento de biblioteca, incluindo cadastro, consulta, atualização e remoção de autores, livros, usuários e empréstimos.


## Funcionalidades

- Autenticação HTTP Basic
- CRUD de Autores, Livros, Usuários e Empréstimos
- Filtros de busca por nome, nacionalidade, título, editora, gênero, datas, etc.
- Validação de entrada e tratamento de erros
- Integração com PostgreSQL (produção) e H2 (testes)
- Migrações Flyway
- Testes automatizados

## Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- PostgreSQL / H2
- Flyway
- Lombok
- JUnit

## Pré-requisitos

- JDK 17 ou superior
- Maven 3.8+
- PostgreSQL rodando na porta 5432
- Banco de dados criado

## Configuração

Clone o repositório:

```sh
git clone https://github.com/SEU_USUARIO/gerenciador-de-biblioteca.git
cd gerenciador-de-biblioteca
```

Crie o banco de dados:

```sql
CREATE DATABASE biblioteca_db;
```

Configure as propriedades do banco em `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

Para rodar os testes, o H2 já está configurado em `src/test/resources/application.properties`.

## Build do Projeto

```sh
mvn clean install
```

## Executando a Aplicação

```sh
mvn spring-boot:run
```

A API estará disponível em: [http://localhost:8080](http://localhost:8080)

## Autenticação

Todas as rotas exigem autenticação HTTP Basic:

- **Usuário:** {Seu Usuario}
- **Senha:** {Sua Senha}

No Insomnia/Postman, configure a aba de autenticação como Basic Auth.

## Principais Endpoints

### Autores

- `POST /api/autores` - Cadastrar novo autor
- `GET /api/autores` - Listar autores
- `PUT /api/autores/{id}` - Atualizar autor
- `DELETE /api/autores/{id}` - Excluir autor

### Livros

- `POST /api/livros` - Cadastrar novo livro
- `GET /api/livros` - Listar livros
- `PUT /api/livros/{id}` - Atualizar livro
- `DELETE /api/livros/{id}` - Excluir livro

### Usuários

- `POST /api/usuarios` - Cadastrar novo usuário
- `GET /api/usuarios` - Listar usuários
- `PUT /api/usuarios/{id}` - Atualizar usuário
- `DELETE /api/usuarios/{id}` - Excluir usuário

### Empréstimos

- `POST /api/emprestimos` - Registrar empréstimo
- `GET /api/emprestimos` - Listar empréstimos

Consulte os controllers para detalhes de filtros e rotas específicas.

## Segurança

- Autenticação via HTTP Basic
- Endpoints protegidos exigem header Authorization

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/backend/gerenciadorDeBiblioteca/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── config/
│   └── resources/
│       └── db/migration/
└── test/
    └── java/
```

## Testes

Execute os testes com:

```sh
mvn test
```

## Migrations

As migrations SQL estão em `src/main/resources/db/migration` e são executadas automaticamente pelo Flyway ao iniciar a aplicação.

