# genie_logiciel

- Si la base ne se lance pas :
  - psql -U postgres
    - Si l'utilisateur n'existe pas :
      - CREATE USER postgres WITH PASSWORD 'postgres';
    - Si l'utilisateur existe mais le mot de passe est incorrect:
      - ALTER USER postgres WITH PASSWORD 'postgres';
  - Si la base n'existe pas :
    - CREATE DATABASE location_borne;
