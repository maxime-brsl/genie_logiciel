package genieLogiciel.projet.borne.util;

import java.util.Scanner;

/**
 * Classe de validation des informations d'un compte
 */

public class CompteValidator {

    private static final Scanner scanner = new Scanner(System.in);
    public static String saisirNom() {
        System.out.println("Saisir le nom : ");
        String nom = scanner.nextLine();
        while (StringValidator.isNom(nom)) {
            System.out.println("Le nom doit être une chaîne de caractères alphabétiques et ne doit pas dépasser 50 caractères.");
            System.out.println("Saisir le nom : ");
            nom = scanner.nextLine();
        }
        return nom;
    }

    public static String saisirPrenom() {
        System.out.println("Saisir le prénom : ");
        String prenom = scanner.nextLine();
        while (StringValidator.isNom(prenom)) {
            System.out.println("Le prénom doit être une chaîne de caractères alphabétiques et ne doit pas dépasser 50 caractères.");
            System.out.println("Saisir le prénom : ");
            prenom = scanner.nextLine();
        }
        return prenom;
    }

    public static String saisirEmail() {
        System.out.println("Saisir l'adresse mail : ");
        String mail = scanner.nextLine();
        while (!StringValidator.isEmail(mail)) {
            System.out.println("L'adresse mail est invalide.");
            System.out.println("Saisir l'adresse mail : ");
            mail = scanner.nextLine();
        }
        return mail;
    }

    public static String saisirAdresse() {
        System.out.println("Saisir l'adresse (NUMERO RUE, CODE_POSTAL VILLE, PAYS): ");
        String adresse = scanner.nextLine();
        while (!StringValidator.isAdresse(adresse)) {
            System.out.println("L'adresse doit est invalide.");
            System.out.println("Format de l'adresse : 13 rue Michel Ney, 54000 Nancy, France");
            System.out.println("Saisir l'adresse : ");
            adresse = scanner.nextLine();
        }
        return adresse;
    }

    public static String saisirNumeroCarteCredit() {
        System.out.println("Saisir le numéro de carte de crédit : ");
        String numeroCarte = scanner.nextLine();
        while (!StringValidator.isCarteCredit(numeroCarte)) {
            System.out.println("Le numéro de carte de crédit est invalide.");
            System.out.println("Le format du numéro de carte de crédit doit être : [Numéro de carte de crédit] [CCV] [Date d'expiration]");
            System.out.println("Saisir le numéro de carte de crédit : ");
            numeroCarte = scanner.nextLine();
        }
        return numeroCarte;
    }

    public static String saisirTelephone() {
        System.out.println("Saisir le numéro de téléphone (avec prefix): ");
        String telephone = scanner.nextLine();
        while (!PhoneNumberValidator.isValidPhoneNumber(telephone)) {
            System.out.println("Le numéro de téléphone est invalide.");
            System.out.println("Le format du numéro de téléphone doit être : +33612345678");
            System.out.println("Saisir le numéro de téléphone : ");
            telephone = scanner.nextLine();
        }
        return telephone;
    }

    public static String saisirMotDePasse() {
        System.out.println("Saisir le mot de passe : ");
        String motDePasse = scanner.nextLine();
        while (!StringValidator.isMotDePasse(motDePasse)) {
            System.out.println("Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial parmis (@#$%^&+=().;,:!?*).");
            System.out.println("Saisir le mot de passe : ");
            motDePasse = scanner.nextLine();
        }
        return motDePasse;
    }
}
