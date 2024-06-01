package genielogiciel.projet.borne.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneNumberValidatorTests {

    @Test
    @DisplayName("Saisie d'un numéro de téléphone sans préfixe")
    void testPhoneNumberWithoutPrefix() {
        String validPhoneNumber = "0123456789";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(validPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone correct")
    void testValidPhoneNumber() {
        String validPhoneNumber = "+33612345678";
        assertTrue(PhoneNumberValidator.isValidPhoneNumber(validPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone trop court")
    void testPhoneNumberTooShort() {
        String invalidPhoneNumber = "123456";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(invalidPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone trop long")
    void testPhoneNumberTooLong() {
        String invalidPhoneNumber = "+336123456789";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(invalidPhoneNumber));
    }

    @Test
    @DisplayName("Saisie absente")
    void testPhoneNumberNull() {
        String nullPhoneNumber = null;
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(nullPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone vide")
    void testEmptyPhoneNumber() {
        String emptyPhoneNumber = "";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(emptyPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone avec un format invalide")
    void testInvalidPhoneNumberFormat() {
        String invalidFormatPhoneNumber = "abcde";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(invalidFormatPhoneNumber));
    }
}
