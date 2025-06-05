CREATE SEQUENCE IF NOT EXISTS autor_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS emprestimo_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS livro_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE autor
(
    id_autor        BIGINT       NOT NULL,
    nome            VARCHAR(60)  NOT NULL,
    nacionalidade   VARCHAR(30)  NOT NULL,
    data_nascimento date         NOT NULL,
    biografia       VARCHAR(500) NOT NULL,
    CONSTRAINT pk_autor PRIMARY KEY (id_autor)
);

CREATE TABLE emprestimo
(
    id_emprestimo   BIGINT NOT NULL,
    data_emprestimo date   NOT NULL,
    data_devolucao  date   NOT NULL,
    id_usuario      UUID,
    id_livro        BIGINT,
    CONSTRAINT pk_emprestimo PRIMARY KEY (id_emprestimo)
);

CREATE TABLE livro
(
    id_livro        BIGINT       NOT NULL,
    titulo          VARCHAR(100) NOT NULL,
    editora         VARCHAR(50)  NOT NULL,
    genero          VARCHAR(30)  NOT NULL,
    data_publicacao date         NOT NULL,
    quantidade      INTEGER      NOT NULL,
    id_autor        BIGINT,
    CONSTRAINT pk_livro PRIMARY KEY (id_livro)
);

CREATE TABLE usuario
(
    id_usuario UUID         NOT NULL,
    cpf        VARCHAR(255) NOT NULL,
    nome       VARCHAR(50)  NOT NULL,
    email      VARCHAR(255) NOT NULL,
    telefone   VARCHAR(15)  NOT NULL,
    endereco   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id_usuario)
);

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_cpf UNIQUE (cpf);

ALTER TABLE emprestimo
    ADD CONSTRAINT FK_EMPRESTIMO_ON_IDLIVRO FOREIGN KEY (id_livro) REFERENCES livro (id_livro);

ALTER TABLE emprestimo
    ADD CONSTRAINT FK_EMPRESTIMO_ON_IDUSUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario);

ALTER TABLE livro
    ADD CONSTRAINT FK_LIVRO_ON_IDAUTOR FOREIGN KEY (id_autor) REFERENCES autor (id_autor);