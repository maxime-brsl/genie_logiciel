package genieLogiciel.projet.borne.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberValidatorTests {

    @Test
    @DisplayName("Saisie d'un numéro de téléphone sans préfixe")
    public void testPhoneNumberWithoutPrefix() {
        String validPhoneNumber = "0123456789";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(validPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone correct")
    public void testValidPhoneNumber() {
        String validPhoneNumber = "+33612345678";
        assertTrue(PhoneNumberValidator.isValidPhoneNumber(validPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone trop court")
    public void testPhoneNumberTooShort() {
        String invalidPhoneNumber = "123456";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(invalidPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone trop long")
    public void testPhoneNumberTooLong() {
        String invalidPhoneNumber = "+336123456789";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(invalidPhoneNumber));
    }

    @Test
    @DisplayName("Saisie absente")
    public void testPhoneNumberNull() {
        String nullPhoneNumber = null;
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(nullPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone vide")
    public void testEmptyPhoneNumber() {
        String emptyPhoneNumber = "";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(emptyPhoneNumber));
    }

    @Test
    @DisplayName("Saisie d'un numéro de téléphone avec un format invalide")
    public void testInvalidPhoneNumberFormat() {
        String invalidFormatPhoneNumber = "abcde";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(invalidFormatPhoneNumber));
    }
}
