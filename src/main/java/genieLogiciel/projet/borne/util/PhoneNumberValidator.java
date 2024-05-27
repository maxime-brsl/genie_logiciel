package genieLogiciel.projet.borne.util;

public class PhoneNumberValidator {
    // Regex pour les numéros de téléphone de différents pays européens
    final static String REGEX_ALLEMAGNE = "(\\+49\\s)?\\d{3}\\s\\d{7,8}";
    final static String REGEX_AUTRICHE = "(\\+43\\s)?\\d{1,4}\\s\\d{1,8}";
    final static String REGEX_BELGIQUE = "(\\+32\\s)?\\d{2}\\s\\d{6,7}";
    final static String REGEX_BULGARIE = "(\\+359\\s)?\\d{1,4}\\s\\d{4,8}";
    final static String REGEX_CROATIE = "(\\+385\\s)?\\d{2,3}\\s\\d{6,7}";
    final static String REGEX_CHYPRE = "(\\+357\\s)?\\d{4}\\s\\d{4}";
    final static String REGEX_REP_TCHEQUE = "(\\+420\\s)?\\d{3}\\s\\d{3}\\s\\d{3}";
    final static String REGEX_DANEMARK = "(\\+45\\s)?\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{2}";
    final static String REGEX_ESTONIE = "(\\+372\\s)?\\d{3}\\s\\d{4}";
    final static String REGEX_FINLANDE = "(\\+358\\s)?\\d{2}\\s\\d{6,7}";
    final static String REGEX_FRANCE = "(\\+33\\s)?\\d{1,2}\\s\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{2}";
    final static String REGEX_GRECE = "(\\+30\\s)?\\d{2,4}\\s\\d{6,8}";
    final static String REGEX_HONGRIE = "(\\+36\\s)?\\d{1,2}\\s\\d{3}\\s\\d{4}";
    final static String REGEX_ISLANDE = "(\\+354\\s)?\\d{3}\\s\\d{4}";
    final static String REGEX_IRELANDE = "(\\+353\\s)?\\d{1,2}\\s\\d{6,8}";
    final static String REGEX_ITALIE = "(\\+39\\s)?\\d{2,4}\\s\\d{6,10}";
    final static String REGEX_LETTONIE = "(\\+371\\s)?\\d{2,3}\\s\\d{6,7}";
    final static String REGEX_LITUANIE = "(\\+370\\s)?\\d{2,3}\\s\\d{5,8}";
    final static String REGEX_LUXEMBOURG = "(\\+352\\s)?\\d{6}\\s\\d{6}";
    final static String REGEX_MALTE = "(\\+356\\s)?\\d{7,8}";
    final static String REGEX_PAYS_BAS = "(\\+31\\s)?\\d{2}\\s\\d{6,9}";
    final static String REGEX_NORVEGE = "(\\+47\\s)?\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{2}";
    final static String REGEX_POLOGNE = "(\\+48\\s)?\\d{2,3}\\s\\d{6,9}";
    final static String REGEX_PORTUGAL = "(\\+351\\s)?\\d{2}\\s\\d{3}\\s\\d{2}\\s\\d{2}";
    final static String REGEX_ROUMANIE = "(\\+40\\s)?\\d{3}\\s\\d{3}\\s\\d{3}";
    final static String REGEX_SLOVAQUIE = "(\\+421\\s)?\\d{2,4}\\s\\d{3}\\s\\d{3}";
    final static String REGEX_SLOVENIE = "(\\+386\\s)?\\d{1,2}\\s\\d{2}\\s\\d{3}\\s\\d{2}";
    final static String REGEX_ESPAGNE = "(\\+34\\s)?\\d{3}\\s\\d{3}\\s\\d{3}";
    final static String REGEX_SUEDE = "(\\+46\\s)?\\d{2,4}\\s\\d{6,10}";
    final static String REGEX_SUISSE = "(\\+41\\s)?\\d{2}\\s\\d{3}\\s\\d{2}\\s\\d{2}";
    final static String REGEX_ROYAUME_UNI = "(\\+44\\s)?\\d{2,5}\\s\\d{4,11}";

    final static String REGEX_EUROPE_PHONE_NUMBERS =
            REGEX_ALLEMAGNE + "|" +
                    REGEX_AUTRICHE + "|" +
                    REGEX_BELGIQUE + "|" +
                    REGEX_BULGARIE + "|" +
                    REGEX_CROATIE + "|" +
                    REGEX_CHYPRE + "|" +
                    REGEX_REP_TCHEQUE + "|" +
                    REGEX_DANEMARK + "|" +
                    REGEX_ESTONIE + "|" +
                    REGEX_FINLANDE + "|" +
                    REGEX_FRANCE + "|" +
                    REGEX_GRECE + "|" +
                    REGEX_HONGRIE + "|" +
                    REGEX_ISLANDE + "|" +
                    REGEX_IRELANDE + "|" +
                    REGEX_ITALIE + "|" +
                    REGEX_LETTONIE + "|" +
                    REGEX_LITUANIE + "|" +
                    REGEX_LUXEMBOURG + "|" +
                    REGEX_MALTE + "|" +
                    REGEX_PAYS_BAS + "|" +
                    REGEX_NORVEGE + "|" +
                    REGEX_POLOGNE + "|" +
                    REGEX_PORTUGAL + "|" +
                    REGEX_ROUMANIE + "|" +
                    REGEX_SLOVAQUIE + "|" +
                    REGEX_SLOVENIE + "|" +
                    REGEX_ESPAGNE + "|" +
                    REGEX_SUEDE + "|" +
                    REGEX_SUISSE + "|" +
                    REGEX_ROYAUME_UNI;

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return phoneNumber.matches(REGEX_EUROPE_PHONE_NUMBERS);
    }
}
