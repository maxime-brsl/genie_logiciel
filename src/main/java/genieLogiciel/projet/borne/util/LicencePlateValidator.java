package genieLogiciel.projet.borne.util;

public class LicencePlateValidator {
    // Regex pour les plaques d'immatriculation de différents pays européens
    final static String REGEX_ALBANIE = "[A-Z]{2}[0-9]{2}[A-Z]{1,2}";
    final static String REGEX_ALLEMAGNE = "[A-Z]{1,3}-[A-Z]{1,2}-[0-9]{1,4}";
    final static String REGEX_ANDORRE = "[0-9]{4}\\s[A-Z]{3}";
    final static String REGEX_AUTRICHE = "[A-Z]{1,3}\\s[0-9]{1,4}";
    final static String REGEX_BELGIQUE = "[0-9]{1,3}-[A-Z]{3}-[0-9]{3}";
    final static String REGEX_BIELORUSSIE = "[0-9]{4}\\s[A-Z]{2}-[0-9]{1}";
    final static String REGEX_BOSNIE_HERZEGOVINE = "[A-Z]{2}-[0-9]{1}-[A-Z]{2}-[0-9]{4}";
    final static String REGEX_BULGARIE = "[A-Z]{2}\\s[0-9]{4}-[A-Z]{1,2}";
    final static String REGEX_CHYPRE = "[A-Z]{3}\\s[0-9]{4}";
    final static String REGEX_CROATIE = "[A-Z]{2}-[0-9]{2,3}-[A-Z]{1,2}";
    final static String REGEX_DANEMARK = "[A-Z]{2}\\s[0-9]{2}\\s[0-9]{2}";
    final static String REGEX_ESPAGNE = "[0-9]{4}\\s[A-Z]{3}";
    final static String REGEX_ESTONIE = "[0-9]{3}-[A-Z]{3}";
    final static String REGEX_FINLANDE = "[A-Z]{3}-[0-9]{3}";
    final static String REGEX_FRANCE = "[A-Z]{2}-[0-9]{3}-[A-Z]{2}";
    final static String REGEX_GRECE = "[A-Z]{2}-[0-9]{3}-[A-Z]{2}";
    final static String REGEX_HONGRIE = "[A-Z]{3}-[0-9]{3}";
    final static String REGEX_IRELANDE = "[0-9]{2}-[A-Z]{1,2}-[0-9]{1,4}";
    final static String REGEX_ISLANDE = "[A-Z]{2}\\s[0-9]{3}";
    final static String REGEX_ITALIE = "[A-Z]{2}[0-9]{3}[A-Z]{2}";
    final static String REGEX_LETTONIE = "[A-Z]{2}-[0-9]{4}";
    final static String REGEX_LIECHTENSTEIN = "[A-Z]{2}\\s[0-9]{4}";
    final static String REGEX_LITUANIE = "[A-Z]{3}-[0-9]{3}";
    final static String REGEX_LUXEMBOURG = "[0-9]{4}\\s[A-Z]{1,2}";
    final static String REGEX_MACEDOINE = "[A-Z]{2}-[0-9]{3}-[A-Z]{2}";
    final static String REGEX_MALTE = "[A-Z]{3}-[0-9]{4}";
    final static String REGEX_MOLDAVIE = "[A-Z]{2}-[0-9]{3}-[A-Z]{2}";
    final static String REGEX_MONACO = "[0-9]{2}\\s[A-Z]{1,2}\\s[0-9]{2}";
    final static String REGEX_MONTENEGRO = "[A-Z]{2}-[0-9]{5}-[A-Z]{1,2}";
    final static String REGEX_NORVEGE = "[A-Z]{2}\\s[0-9]{5}";
    final static String REGEX_PAYS_BAS = "[0-9]{2}-[A-Z]{2}-[0-9]{2}";
    final static String REGEX_POLOGNE = "[A-Z]{3}\\s[0-9]{4}";
    final static String REGEX_PORTUGAL = "[0-9]{2}-[0-9]{2}-[A-Z]{2}-[0-9]{1}";
    final static String REGEX_REP_TCHEQUE = "[0-9]{2}[A-Z]{2}\\s[0-9]{4}";
    final static String REGEX_ROUMANIE = "[A-Z]{2}-[0-9]{3}-[A-Z]{3}";
    final static String REGEX_ROYAUME_UNI = "[A-Z]{2}[0-9]{2}\\s[A-Z]{3}";
    final static String REGEX_RUSSIE = "[0-9]{3}\\s[0-9]{3}";
    final static String REGEX_SAINT_MARIN = "[A-Z]{1,2}\\s[0-9]{3}";
    final static String REGEX_SERBIE = "[A-Z]{2}-[0-9]{3}-[A-Z]{2}";
    final static String REGEX_SLOVAQUIE = "[A-Z]{2}[0-9]{2}[A-Z]{1,2}";
    final static String REGEX_SLOVENIE = "[A-Z]{2}-[0-9]{2}-[A-Z]{2}";
    final static String REGEX_SUEDE = "[A-Z]{3}\\s[0-9]{2}\\s[A-Z]{1}";
    final static String REGEX_SUISSE = "[A-Z]{2}\\s[0-9]{4}";
    final static String REGEX_UKRAINE = "[0-9]{2}-[0-9]{4}[A-Z]{2}";
    final static String REGEX_VATICAN = "[0-9]{1,2}-[A-Z]{1,3}-[0-9]{1,2}";

    final static String REGEX_EUROPE_PLATES =
            REGEX_ALBANIE + "|" +
                    REGEX_ALLEMAGNE + "|" +
                    REGEX_ANDORRE + "|" +
                    REGEX_AUTRICHE + "|" +
                    REGEX_BELGIQUE + "|" +
                    REGEX_BIELORUSSIE + "|" +
                    REGEX_BOSNIE_HERZEGOVINE + "|" +
                    REGEX_BULGARIE + "|" +
                    REGEX_CHYPRE + "|" +
                    REGEX_CROATIE + "|" +
                    REGEX_DANEMARK + "|" +
                    REGEX_ESPAGNE + "|" +
                    REGEX_ESTONIE + "|" +
                    REGEX_FINLANDE + "|" +
                    REGEX_FRANCE + "|" +
                    REGEX_GRECE + "|" +
                    REGEX_HONGRIE + "|" +
                    REGEX_IRELANDE + "|" +
                    REGEX_ISLANDE + "|" +
                    REGEX_ITALIE + "|" +
                    REGEX_LETTONIE + "|" +
                    REGEX_LIECHTENSTEIN + "|" +
                    REGEX_LITUANIE + "|" +
                    REGEX_LUXEMBOURG + "|" +
                    REGEX_MACEDOINE + "|" +
                    REGEX_MALTE + "|" +
                    REGEX_MOLDAVIE + "|" +
                    REGEX_MONACO + "|" +
                    REGEX_MONTENEGRO + "|" +
                    REGEX_NORVEGE + "|" +
                    REGEX_PAYS_BAS + "|" +
                    REGEX_POLOGNE + "|" +
                    REGEX_PORTUGAL + "|" +
                    REGEX_REP_TCHEQUE + "|" +
                    REGEX_ROUMANIE + "|" +
                    REGEX_ROYAUME_UNI + "|" +
                    REGEX_RUSSIE + "|" +
                    REGEX_SAINT_MARIN + "|" +
                    REGEX_SERBIE + "|" +
                    REGEX_SLOVAQUIE + "|" +
                    REGEX_SLOVENIE + "|" +
                    REGEX_SUEDE + "|" +
                    REGEX_SUISSE + "|" +
                    REGEX_UKRAINE + "|" +
                    REGEX_VATICAN;
    public static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            return false;
        }
        return licensePlate.toUpperCase().matches(REGEX_EUROPE_PLATES);
    }
}
