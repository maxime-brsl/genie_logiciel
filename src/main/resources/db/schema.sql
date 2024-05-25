DROP TABLE IF EXISTS client;

CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    mail Text NOT NULL,
    adresse Text NOT NULL,
    numero_debit VARCHAR(16) NOT NULL,
    numero_tel VARCHAR(10) NOT NULL
);




