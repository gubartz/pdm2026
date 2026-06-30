DROP TABLE IF EXISTS usuario_filme;
DROP TABLE IF EXISTS filme_genero;
DROP TABLE IF EXISTS filme;
DROP TABLE IF EXISTS classificacao;
DROP TABLE IF EXISTS genero;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS status;

CREATE TABLE usuario(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT NOT NULL,
    nome TEXT NOT NULL,
    senha TEXT NOT NULL
);

INSERT INTO usuario VALUES (1, 'jose@teste.com.br', 'José da Silva', '123');

CREATE TABLE filme(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    titulo_original TEXT NOT NULL,
    data_lancamento TEXT NOT NULL,
    duracao INT NOT NULL,
    sinopse TEXT NOT NULL
);

CREATE TABLE classificacao(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descricao TEXT NOT NULL
);

INSERT INTO classificacao VALUES (1, 'Ruim');
INSERT INTO classificacao VALUES (2, 'Bom');
INSERT INTO classificacao VALUES (3, 'Ótimo');

CREATE TABLE status(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descricao TEXT NOT NULL
);

INSERT INTO status VALUES (1, 'Não Assistido');
INSERT INTO status VALUES (2, 'Assistido');

CREATE TABLE usuario_filme(
    usuario_id INTEGER NOT NULL,
    filme_id INTEGER NOT NULL,
    classificacao_id INTEGER NOT NULL,
    status_id INTEGER NOT NULL,
    FOREIGN KEY(usuario_id) REFERENCES usuario(id),
    FOREIGN KEY(filme_id) REFERENCES filme(id),
    FOREIGN KEY(classificacao_id) REFERENCES classificacao(id)
    FOREIGN KEY(status_id) REFERENCES status(id)
);

CREATE TABLE genero(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descricao TEXT NOT NULL
);

INSERT INTO genero VALUES (1, 'Terror');
INSERT INTO genero VALUES (2, 'Comédia');
INSERT INTO genero VALUES (3, 'Romance');

CREATE TABLE filme_genero(
    filme_id INT NOT NULL,
    genero_id INT NOT NULL,
    FOREIGN KEY(filme_id) REFERENCES filme(id),
    FOREIGN KEY(genero_id) REFERENCES filme(genero)
);