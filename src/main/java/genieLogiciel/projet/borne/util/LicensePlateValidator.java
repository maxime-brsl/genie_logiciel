package genielogiciel.projet.borne.util;

public class LicensePlateValidator {

    // Regex pour les plaques d'immatriculation de différents pays européens
    static final String REGEX_ALBANIE = "[A-Z]{2}\\d{2}[A-Z]{1,2}";
    static final String REGEX_ALLEMAGNE = "[A-Z]{1,3}-[A-Z]{1,2}-\\d{1,4}";
    static final String REGEX_ANDORRE = "\\d{4}\\s[A-Z]{3}";
    static final String REGEX_AUTRICHE = "[A-Z]{1,3}\\s\\d{1,4}";
    static final String REGEX_BELGIQUE = "\\d{1,3}-[A-Z]{3}-\\d{3}";
    static final String REGEX_BIELORUSSIE = "\\d{4}\\s[A-Z]{2}-\\d";
    static final String REGEX_BOSNIE_HERZEGOVINE = "[A-Z]{2}-\\d-[A-Z]{2}-\\d{4}";
    static final String REGEX_BULGARIE = "[A-Z]{2}\\s\\d{4}-[A-Z]{1,2}";
    static final String REGEX_CHYPRE = "[A-Z]{3}\\s\\d{4}";
    static final String REGEX_CROATIE = "[A-Z]{2}-\\d{2,3}-[A-Z]{1,2}";
    static final String REGEX_DANEMARK = "[A-Z]{2}\\s\\d{2}\\s\\d{2}";
    static final String REGEX_ESPAGNE = "\\d{4}\\s[A-Z]{3}";
    static final String REGEX_ESTONIE = "\\d{3}-[A-Z]{3}";
    static final String REGEX_FINLANDE = "[A-Z]{3}-\\d{3}";
    static final String REGEX_FRANCE = "[A-Z]{2}-\\d{3}-[A-Z]{2}";
    static final String REGEX_GRECE = "[A-Z]{2}-\\d{3}-[A-Z]{2}";
    static final String REGEX_HONGRIE = "[A-Z]{3}-\\d{3}";
    static final String REGEX_IRELANDE = "\\d{2}-[A-Z]{1,2}-\\d{1,4}";
    static final String REGEX_ISLANDE = "[A-Z]{2}\\s\\d{3}";
    static final String REGEX_ITALIE = "[A-Z]{2}\\d{3}[A-Z]{2}";
    static final String REGEX_LETTONIE = "[A-Z]{2}-\\d{4}";
    static final String REGEX_LIECHTENSTEIN = "[A-Z]{2}\\s\\d{4}";
    static final String REGEX_LITUANIE = "[A-Z]{3}-\\d{3}";
    static final String REGEX_LUXEMBOURG = "\\d{4}\\s[A-Z]{1,2}";
    static final String REGEX_MACEDOINE = "[A-Z]{2}-\\d{3}-[A-Z]{2}";
    static final String REGEX_MALTE = "[A-Z]{3}-\\d{4}";
    static final String REGEX_MOLDAVIE = "[A-Z]{2}-\\d{3}-[A-Z]{2}";
    static final String REGEX_MONACO = "\\d{2}\\s[A-Z]{1,2}\\s\\d{2}";
    static final String REGEX_MONTENEGRO = "[A-Z]{2}-\\d{5}-[A-Z]{1,2}";
    static final String REGEX_NORVEGE = "[A-Z]{2}\\s\\d{5}";
    static final String REGEX_PAYS_BAS = "\\d{2}-[A-Z]{2}-\\d{2}";
    static final String REGEX_POLOGNE = "[A-Z]{3}\\s\\d{4}";
    static final String REGEX_PORTUGAL = "\\d{2}-\\d{2}-[A-Z]{2}-\\d";
    static final String REGEX_REP_TCHEQUE = "\\d{2}[A-Z]{2}\\s\\d{4}";
    static final String REGEX_ROUMANIE = "[A-Z]{2}-\\d{3}-[A-Z]{3}";
    static final String REGEX_ROYAUME_UNI = "[A-Z]{2}\\d{2}\\s[A-Z]{3}";
    static final String REGEX_RUSSIE = "\\d{3}\\s\\d{3}";
    static final String REGEX_SAINT_MARIN = "[A-Z]{1,2}\\s\\d{3}";
    static final String REGEX_SERBIE = "[A-Z]{2}-\\d{3}-[A-Z]{2}";
    static final String REGEX_SLOVAQUIE = "[A-Z]{2}\\d{2}[A-Z]{1,2}";
    static final String REGEX_SLOVENIE = "[A-Z]{2}-\\d{2}-[A-Z]{2}";
    static final String REGEX_SUEDE = "[A-Z]{3}\\s\\d{2}\\s[A-Z]";
    static final String REGEX_SUISSE = "[A-Z]{2}\\s\\d{4}";
    static final String REGEX_UKRAINE = "\\d{2}-\\d{4}[A-Z]{2}";
    static final String REGEX_VATICAN = "\\d{1,2}-[A-Z]{1,3}-\\d{1,2}";
    static final String REGEX_EUROPE_PLATES =
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

    private LicensePlateValidator() {
    }

    public static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            return false;
        }
        return licensePlate.toUpperCase().matches(REGEX_EUROPE_PLATES);
    }
}
