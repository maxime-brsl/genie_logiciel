DROP TABLE IF EXISTS client;

CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    numero_carte_bancaire VARCHAR(16) NOT NULL,
    telephone VARCHAR(10) NOT NULL
);
