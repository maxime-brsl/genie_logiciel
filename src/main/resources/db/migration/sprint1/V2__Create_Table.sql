DROP TABLE IF EXISTS client CASCADE;
DROP TABLE IF EXISTS vehicule CASCADE;
DROP TABLE IF EXISTS reservation CASCADE;
DROP TABLE IF EXISTS borne CASCADE;
DROP TABLE IF EXISTS facture CASCADE;

DROP TYPE IF EXISTS enum_reservation;
DROP TYPE IF EXISTS enum_borne;

CREATE TYPE enum_reservation AS ENUM ('EN_ATTENTE', 'EN_COURS', 'TERMINEE');
CREATE TYPE enum_borne AS ENUM ('DISPONIBLE', 'INDISPONIBLE', 'OCCUPEE', 'RESERVEE');

CREATE TABLE client
(
    id_client    SERIAL PRIMARY KEY,
    nom          VARCHAR(50) NOT NULL,
    prenom       VARCHAR(50) NOT NULL,
    mail         Text        NOT NULL,
    adresse      Text        NOT NULL,
    numero_debit Text        NOT NULL,
    numero_tel   Text        NOT NULL UNIQUE,
    mot_de_passe Text        NOT NULL
);

CREATE TABLE vehicule
(
    id_vehicule            SERIAL PRIMARY KEY,
    client_id              INTEGER     NOT NULL,
    plaque_immatriculation VARCHAR(50) NOT NULL,
    loue                   BOOLEAN     NOT NULL
);

CREATE TABLE borne
(
    id_borne       SERIAL PRIMARY KEY,
    reservation_id INTEGER    NULL,
    etat_borne     enum_borne NOT NULL
);


CREATE TABLE reservation
(
    id_reservation   SERIAL PRIMARY KEY,
    client_id        INTEGER          NOT NULL,
    vehicule_id      INTEGER          NOT NULL,
    borne_id         INTEGER          NOT NULL,
    facture_id       INTEGER          NULL,
    heure_debut      TIMESTAMP        NOT NULL,
    heure_fin_prevue TIMESTAMP        NOT NULL,
    heure_fin_reelle TIMESTAMP,
    etat_res         enum_reservation NOT NULL
);

CREATE TABLE facture
(
    id_facture     SERIAL PRIMARY KEY,
    reservation_id INTEGER NOT NULL,
    montant        FLOAT   NOT NULL
);

ALTER TABLE reservation
    ADD FOREIGN KEY (client_id) REFERENCES client (id_client);
ALTER TABLE reservation
    ADD FOREIGN KEY (vehicule_id) REFERENCES vehicule (id_vehicule);
ALTER TABLE reservation
    ADD FOREIGN KEY (borne_id) REFERENCES borne (id_borne);
ALTER TABLE reservation
    ADD FOREIGN KEY (facture_id) REFERENCES facture (id_facture);
ALTER TABLE vehicule
    ADD FOREIGN KEY (client_id) REFERENCES client (id_client);
ALTER TABLE borne
    ADD FOREIGN KEY (reservation_id) REFERENCES reservation (id_reservation);
ALTER TABLE facture
    ADD FOREIGN KEY (reservation_id) REFERENCES reservation (id_reservation);