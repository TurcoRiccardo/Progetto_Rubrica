CREATE DATABASE IF NOT EXISTS rubrica_db;
USE rubrica_db;


CREATE TABLE IF NOT EXISTS persone (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50),
    indirizzo VARCHAR(100),
    telefono VARCHAR(20),
    eta INT
);

CREATE TABLE IF NOT EXISTS utenti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);


INSERT INTO persone (nome, cognome, indirizzo, telefono, eta)
VALUES
('Steve', 'Jobs', 'via Cupertino 13', '0612344', 56),
('Bill', 'Gates', 'via Redmond 10', '06688989', 60),
('Babbo', 'Natale', 'via del Polo Nord', '00000111', 99);

INSERT INTO utenti (username, password)
VALUES
('admin', 'admin'),
('user', 'user');