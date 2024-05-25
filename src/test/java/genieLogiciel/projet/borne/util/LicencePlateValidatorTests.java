package genieLogiciel.projet.borne.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LicencePlateValidatorTests {

    @Test
    public void testIsValidLicensePlate_ValidPlate() {
        String validPlate = "AB123CD";
        assertTrue(LicencePlateValidator.isValidLicensePlate(validPlate));
    }

    @Test
    public void testIsValidLicensePlate_InvalidPlate() {
        String invalidPlate = "Invalid";
        assertFalse(LicencePlateValidator.isValidLicensePlate(invalidPlate));
    }

    @Test
    public void testIsValidLicensePlate_NullPlate() {
        assertFalse(LicencePlateValidator.isValidLicensePlate(null));
    }

    @Test
    public void testIsValidLicensePlate_EmptyPlate() {
        String emptyPlate = "";
        assertFalse(LicencePlateValidator.isValidLicensePlate(emptyPlate));
    }

    @Test
    public void testIsValidLicensePlate_LowerCasePlate() {
        String lowerCasePlate = "ab123cd";
        assertTrue(LicencePlateValidator.isValidLicensePlate(lowerCasePlate));
    }

    @Test
    public void testIsValidLicensePlate_NonLatinCharacters() {
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
            assertFalse(LicencePlateValidator.isValidLicensePlate(plate));
        }
    }

    @Test
    public void testIsValidLicensePlate_SpecialCharacters() {
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
            assertFalse(LicencePlateValidator.isValidLicensePlate(plate));
        }
    }
}
