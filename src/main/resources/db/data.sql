INSERT INTO client (nom, prenom, mail, adresse, numero_debit, numero_tel)
VALUES ('Brasley', 'Maxime', 'maxime.brasley@gmail.com' ,'10 rue pierre chalnot', '1234567890123456', '0680702581');

INSERT INTO vehicule (client_id, plaque_immatriculation, loue)
VALUES (1, 'AA-123-AA', false);

INSERT INTO borne (etat_borne)
VALUES ('disponible');

INSERT INTO reservation (client_id, vehicule_id, borne_id, numero_compte, solde, heure_debut, heure_fin_prevue, etat_res)
VALUES (1, 1, 1, '1234567890123456', 100.00, '2020-01-01 00:00:00', '2020-01-01 01:00:00', 'disponible');


INSERT INTO facture (reservation_id, montant)
VALUES (1, 100.00);

