package genielogiciel.projet.borne.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringValidatorTests {

    @Test
    @DisplayName("Test de validation du nom - valide")
    void testIsNomValide() {
        assertTrue(StringValidator.isNom("Dupont"), "Nom valide");
    }

    @Test
    @DisplayName("Test de validation du nom - invalide (chiffres)")
    void testIsNomInvalideChiffres() {
        assertFalse(StringValidator.isNom("123"), "Nom invalide (chiffres)");
    }

    @Test
    @DisplayName("Test de validation du nom - invalide (longueur)")
    void testIsNomInvalideLongueur() {
        assertFalse(StringValidator.isNom("TrèsLongNomQuiDépasseLaLimiteAutorisée"), "Nom invalide (longueur)");
    }

    @Test
    @DisplayName("Test de validation du nom - null")
    void testIsNomNull() {
        assertFalse(StringValidator.isNom(null), "Nom null");
    }

    @Test
    @DisplayName("Test de validation de l'email - valide")
    void testIsEmailValide() {
        assertTrue(StringValidator.isEmail("email@example.com"), "Email valide");
    }

    @Test
    @DisplayName("Test de validation de l'email - invalide")
    void testIsEmailInvalide() {
        assertFalse(StringValidator.isEmail("email.example.com"), "Email invalide");
        assertFalse(StringValidator.isEmail("email@localhost"), "Email invalide");
    }

    @Test
    @DisplayName("Test de validation de l'email - null")
    void testIsEmailNull() {
        assertFalse(StringValidator.isEmail(null), "Email null");
    }

    @Test
    @DisplayName("Test de validation de l'adresse - valide")
    void testIsAdresseValide() {
        assertTrue(StringValidator.isAdresse("123 rue du Paradis, 75001 Paris, France"), "Adresse valide");
    }

    @Test
    @DisplayName("Test de validation de l'adresse - invalide")
    void testIsAdresseInvalide() {
        assertFalse(StringValidator.isAdresse("Address with invalid characters $&@"), "Adresse invalide");
    }

    @Test
    @DisplayName("Test de validation de l'adresse - null")
    void testIsAdresseNull() {
        assertFalse(StringValidator.isAdresse(null), "Adresse null");
    }

    @Test
    @DisplayName("Test de validation du numéro de carte de crédit - valide")
    void testIsCarteCreditValide() {
        assertTrue(StringValidator.isCarteCredit("1234567890123456 123 01/26"), "Carte de crédit valide");
    }

    @Test
    @DisplayName("Test de validation du numéro de carte de crédit - invalide (longueur)")
    void testIsCarteCreditInvalideLongueur() {
        assertFalse(StringValidator.isCarteCredit("12345 678901234567 123 01/26"), "Carte de crédit invalide (longueur)");
    }

    @Test
    @DisplayName("Test de validation du numéro de carte de crédit - invalide (caractères)")
    void testIsCarteCreditInvalideCaracteres() {
        assertFalse(StringValidator.isCarteCredit("1234567890123456 ABC 01/26"), "Carte de crédit invalide (caractères)");
    }

    @Test
    @DisplayName("Test de validation du numéro de carte de crédit - invalide (mois)")
    void testIsCarteCreditInvalideMois() {
        assertFalse(StringValidator.isCarteCredit("1234567890123456 123 13/26"), "Carte de crédit invalide (mois)");
    }

    @Test
    @DisplayName("Test de validation du numéro de carte de crédit - null")
    void testIsCarteCreditNull() {
        assertFalse(StringValidator.isCarteCredit(null), "Carte de crédit null");
    }

    @Test
    @DisplayName("Test de validation du mot de passe - valide")
    void testIsMotDePasseValide() {
        assertTrue(StringValidator.isMotDePasse("Password1@"), "Mot de passe valide");
    }

    @Test
    @DisplayName("Test de validation du mot de passe - invalide (pas de majuscule)")
    void testIsMotDePasseInvalideMajuscule() {
        assertFalse(StringValidator.isMotDePasse("password"), "Mot de passe invalide (pas de majuscule)");
    }

    @Test
    @DisplayName("Test de validation du mot de passe - invalide (pas de minuscule)")
    void testIsMotDePasseInvalideMinuscule() {
        assertFalse(StringValidator.isMotDePasse("PASSWORD1@"), "Mot de passe invalide (pas de minuscule)");
    }

    @Test
    @DisplayName("Test de validation du mot de passe - invalide (pas de caractère spécial)")
    void testIsMotDePasseInvalideCaractereSpecial() {
        assertFalse(StringValidator.isMotDePasse("Password1"), "Mot de passe invalide (pas de caractère spécial)");
    }

    @Test
    @DisplayName("Test de validation du mot de passe - invalide (trop court)")
    void testIsMotDePasseInvalideLongueur() {
        assertFalse(StringValidator.isMotDePasse("Pass1@"), "Mot de passe invalide (trop court)");
    }

    @Test
    @DisplayName("Test de validation du mot de passe - null")
    void testIsMotDePasseNull() {
        assertFalse(StringValidator.isMotDePasse(null), "Mot de passe null");
    }
}
