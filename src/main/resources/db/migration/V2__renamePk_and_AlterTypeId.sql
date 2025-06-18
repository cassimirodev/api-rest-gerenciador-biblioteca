ALTER TABLE livro
    DROP CONSTRAINT fk_livro_on_idautor;

ALTER TABLE autor
    ADD autor_id UUID;

ALTER TABLE livro
    ADD autor_id UUID;

ALTER TABLE livro
    ADD CONSTRAINT FK_LIVRO_ON_AUTOR FOREIGN KEY (autor_id) REFERENCES autor (autor_id);

ALTER TABLE autor
    DROP COLUMN id_autor;

ALTER TABLE livro
    DROP COLUMN id_autor;

ALTER TABLE livro
    DROP COLUMN id_livro;

DROP SEQUENCE autor_seq CASCADE;

DROP SEQUENCE emprestimo_seq CASCADE;

DROP SEQUENCE livro_seq CASCADE;

ALTER TABLE emprestimo
    DROP COLUMN id_emprestimo;

ALTER TABLE emprestimo
    DROP COLUMN id_livro;

ALTER TABLE emprestimo
    ADD id_emprestimo UUID NOT NULL PRIMARY KEY;

ALTER TABLE emprestimo
    ADD id_livro UUID;

ALTER TABLE emprestimo
    ADD CONSTRAINT FK_EMPRESTIMO_ON_ID_LIVRO FOREIGN KEY (id_livro) REFERENCES livro (id_livro);

ALTER TABLE livro
    ADD id_livro UUID NOT NULL PRIMARY KEY;

ALTER TABLE autor
    ADD CONSTRAINT pk_autor PRIMARY KEY (autor_id);