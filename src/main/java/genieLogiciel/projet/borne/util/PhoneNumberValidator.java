package genielogiciel.projet.borne.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneNumberValidator {

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    private PhoneNumberValidator() {
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        try {
            // On parse le numéro de téléphone pour le saisir sans code région
            PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, null);
            return phoneNumberUtil.isValidNumber(parsedPhoneNumber);
        } catch (NumberParseException e) {
            return false;
        }
    }
}
