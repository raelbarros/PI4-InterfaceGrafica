CREATE DATABASE jdbc_test;

USE jdbc_test;

CREATE TABLE cliente (
    cliente_id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(200) NOT NULL,
    data_nasc DATE NOT NULL,
    sexo VARCHAR(10),
    enabled BOOLEAN
);