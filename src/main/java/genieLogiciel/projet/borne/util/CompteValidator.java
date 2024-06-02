package genielogiciel.projet.borne.util;

import genielogiciel.projet.borne.menu.ReservationMenu;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Classe de validation des informations d'un compte
 */

public class CompteValidator {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(ReservationMenu.class));

    private CompteValidator() {
    }

    public static String saisirNom() {
        LOG.info(TextMenu.SAISIR_NOM);
        String nom = scanner.nextLine();
        while (!StringValidator.isNom(nom)) {
            LOG.info("Le nom " + TextMenu.PRENOM_NOM_INVALIDE);
            LOG.info(TextMenu.SAISIR_NOM);
            nom = scanner.nextLine();
        }
        return nom;
    }

    public static String saisirPrenom() {
        LOG.info(TextMenu.SAISIR_PRENOM);
        String prenom = scanner.nextLine();
        while (!StringValidator.isNom(prenom)) {
            LOG.info("Le prénom " + TextMenu.PRENOM_NOM_INVALIDE);
            LOG.info(TextMenu.SAISIR_PRENOM);
            prenom = scanner.nextLine();
        }
        return prenom;
    }

    public static String saisirEmail() {
        LOG.info(TextMenu.SAISIR_EMAIL);
        String mail = scanner.nextLine();
        while (!StringValidator.isEmail(mail)) {
            LOG.info(TextMenu.ERREUR_EMAIL);
            LOG.info(TextMenu.SAISIR_EMAIL);
            mail = scanner.nextLine();
        }
        return mail;
    }

    public static String saisirAdresse() {
        LOG.info(TextMenu.SAISIR_ADRESSE);
        String adresse = scanner.nextLine();
        while (!StringValidator.isAdresse(adresse)) {
            LOG.info(TextMenu.ERREUR_ADRESSE);
            LOG.info("Format de l'adresse : 13 rue Michel Ney, 54000 Nancy, France");
            LOG.info(TextMenu.SAISIR_ADRESSE);
            adresse = scanner.nextLine();
        }
        return adresse;
    }

    public static String saisirNumeroCarteCredit() {
        LOG.info(TextMenu.SAISIR_CARTE_CREDIT);
        String numeroCarte = scanner.nextLine();
        while (!StringValidator.isCarteCredit(numeroCarte)) {
            LOG.info(TextMenu.CARTE_CREDIT_INVALIDE);
            LOG.info("Le format du numéro de carte de crédit doit être : [Numéro de carte de crédit] [CCV] [Date d'expiration]");
            LOG.info(TextMenu.SAISIR_CARTE_CREDIT);
            numeroCarte = scanner.nextLine();
        }
        return numeroCarte;
    }

    public static String saisirTelephone() {
        LOG.info(TextMenu.SAISIR_TELEPHONE);
        String telephone = scanner.nextLine();
        while (!PhoneNumberValidator.isValidPhoneNumber(telephone)) {
            LOG.info(TextMenu.TELEPHONE_INVALIDE);
            LOG.info("Le format du numéro de téléphone doit être : +33612345678");
            LOG.info(TextMenu.SAISIR_TELEPHONE);
            telephone = scanner.nextLine();
        }
        return telephone;
    }

    public static String saisirMotDePasse() {
        LOG.info(TextMenu.SAISIR_MOT_DE_PASSE);
        String motDePasse = scanner.nextLine();
        while (!StringValidator.isMotDePasse(motDePasse)) {
            LOG.info("Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial parmi (@#$%^&+=().;,:!?*).");
            LOG.info(TextMenu.SAISIR_MOT_DE_PASSE);
            motDePasse = scanner.nextLine();
        }
        return motDePasse;
    }
}
