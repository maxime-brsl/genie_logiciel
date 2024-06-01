package genielogiciel.projet.borne.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LicensePlateValidatorTests {

    @Test
    void testIsValidLicensePlate_ValidPlate() {
        String validPlate = "AB123CD";
        assertTrue(LicensePlateValidator.isValidLicensePlate(validPlate));
    }

    @Test
    void testIsValidLicensePlate_InvalidPlate() {
        String invalidPlate = "Invalid";
        assertFalse(LicensePlateValidator.isValidLicensePlate(invalidPlate));
    }

    @Test
    void testIsValidLicensePlate_NullPlate() {
        assertFalse(LicensePlateValidator.isValidLicensePlate(null));
    }

    @Test
    void testIsValidLicensePlate_EmptyPlate() {
        String emptyPlate = "";
        assertFalse(LicensePlateValidator.isValidLicensePlate(emptyPlate));
    }

    @Test
    void testIsValidLicensePlate_LowerCasePlate() {
        String lowerCasePlate = "ab123cd";
        assertTrue(LicensePlateValidator.isValidLicensePlate(lowerCasePlate));
    }

    @Test
    void testIsValidLicensePlate_NonLatinCharacters() {
        String[] nonLatinCharacters = {
                "ÄB123CD",
                "ÑB123CD",
                "ΑΒ123ΓΔ",
                "АБ123CD",
                "אב123גד",
                "あいう123えお",
                "가나다123라마",
                "أبج123ده",
                "अब123चद",
                "აბ123გდ",
                "กข123คง",
                "آب123جد",
        };

        for (String plate : nonLatinCharacters) {
            assertFalse(LicensePlateValidator.isValidLicensePlate(plate));
        }
    }

    @Test
    void testIsValidLicensePlate_SpecialCharacters() {
        Set<String> specialCharacters = new HashSet<>();
        specialCharacters.add("AB123CD!");
        specialCharacters.add("AB123CD@");
        specialCharacters.add("AB123CD#");
        specialCharacters.add("AB123CD$");
        specialCharacters.add("AB123CD%");
        specialCharacters.add("AB123CD^");
        specialCharacters.add("AB123CD&");
        specialCharacters.add("AB123CD*");
        specialCharacters.add("AB123CD(");
        specialCharacters.add("AB123CD)");
        specialCharacters.add("AB123CD-");
        specialCharacters.add("AB123CD_");
        specialCharacters.add("AB123CD=");
        specialCharacters.add("AB123CD+");
        specialCharacters.add("AB123CD[");
        specialCharacters.add("AB123CD]");
        specialCharacters.add("AB123CD{");
        specialCharacters.add("AB123CD}");
        specialCharacters.add("AB123CD;");
        specialCharacters.add("AB123CD:");
        specialCharacters.add("AB123CD'");
        specialCharacters.add("AB123CD\"");
        specialCharacters.add("AB123CD,");
        specialCharacters.add("AB123CD<");
        specialCharacters.add("AB123CD.");
        specialCharacters.add("AB123CD>");
        specialCharacters.add("AB123CD/");
        specialCharacters.add("AB123CD?");
        specialCharacters.add("AB123CD|");
        specialCharacters.add("AB123CD\\");
        specialCharacters.add("AB123CD~");
        specialCharacters.add("AB123CD`");

        for (String plate : specialCharacters) {
            assertFalse(LicensePlateValidator.isValidLicensePlate(plate));
        }
    }
}
