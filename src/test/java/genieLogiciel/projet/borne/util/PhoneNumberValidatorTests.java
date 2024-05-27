package genieLogiciel.projet.borne.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberValidatorTests {

    @Test
    public void testValidPhoneNumber() {
        // Numéro de téléphone sans préfixe
        String validPhoneNumber = "0123456789";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(validPhoneNumber));
    }

    @Test
    public void testValidPhoneNumber2() {
        // Numéro de téléphone valide
        String validPhoneNumber = "+33612345678";
        assertTrue(PhoneNumberValidator.isValidPhoneNumber(validPhoneNumber));
    }

    @Test
    public void testInvalidPhoneNumber() {
        // Numéro de téléphone invalide
        String invalidPhoneNumber = "123456";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(invalidPhoneNumber));
    }

    @Test
    public void testNullPhoneNumber() {
        // Numéro de téléphone null
        String nullPhoneNumber = null;
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(nullPhoneNumber));
    }

    @Test
    public void testEmptyPhoneNumber() {
        // Numéro de téléphone vide
        String emptyPhoneNumber = "";
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(emptyPhoneNumber));
    }

    @Test
    public void testInvalidPhoneNumberFormat() {
        // Format de numéro de téléphone invalide
        String invalidFormatPhoneNumber = "abcde"; // Format invalide
        assertFalse(PhoneNumberValidator.isValidPhoneNumber(invalidFormatPhoneNumber));
    }
}
