INSERT INTO client (nom, prenom, mail, adresse, numero_debit, numero_tel, mot_de_passe)
VALUES ('Brasley', 'Maxime', 'maxime.brasley@gmail.com' ,'10 rue pierre chalnot, 54000 Nancy, France', '1234567890123456 123 01/06', '+33680702581', 'MDP');

INSERT INTO vehicule (client_id, plaque_immatriculation, loue)
VALUES (1, 'AA-123-AA', false);

INSERT INTO borne (etat_borne)
VALUES ('disponible');

INSERT INTO reservation (client_id, vehicule_id, borne_id, heure_debut, heure_fin_prevue, etat_res)
VALUES (1, 1, 1, '2020-01-01 00:00:00', '2020-01-01 01:00:00', 'disponible');

INSERT INTO facture (reservation_id, montant)
VALUES (1, 100.00);

