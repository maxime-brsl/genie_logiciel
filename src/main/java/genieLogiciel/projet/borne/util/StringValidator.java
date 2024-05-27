package genieLogiciel.projet.borne.util;

public class StringValidator {

    private static final int MAX_LENGTH_NOM = 50;
    private static final String REGEX_MAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String REGEX_NOM = "[a-zA-Z-]+";
    private static final String REGEX_ADRESSE = "^[0-9]+(?:TER|BIS)? [a-zA-Z-]+, [0-9]+ [a-zA-Z-]+, [a-zA-Z-]+$";
    private static final String REGEX_CARTE_CREDIT = "^\\d{16}\\s\\d{3}\\s(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\d{2}$\n";
    private static final String REGEX_MOT_DE_PASSE = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";


    /**
     * Vérifie si l'entrée est un nom valide
     *
     * @param input le nom à vérifier
     * @return true si le nom est valide, false sinon
     */
    public static boolean isNom(final String input) {
        return input == null || !input.matches(REGEX_NOM) || isMaxLength(input, MAX_LENGTH_NOM);
    }

    /**
     * Vérifie si l'entrée est une adresse email valide
     *
     * @param input l'adresse email à vérifier
     * @return true si l'adresse email est valide, false sinon
     */
    public static boolean isEmail(final String input) {
        return input != null && input.matches(REGEX_MAIL);
    }

    public static boolean isAdresse(final String input) {
        return input != null && input.matches(REGEX_ADRESSE);
    }

    public static boolean isCarteCredit(final String input) {
        return input != null && input.matches(REGEX_CARTE_CREDIT);
    }

    public static boolean isMotDePasse(final String input) {
        return input != null && input.matches(REGEX_MOT_DE_PASSE);
    }

    /**
     * Vérifie si la longueur de l'entrée ne dépasse pas une certaine longueur
     *
     * @param input     l'entrée à vérifier
     * @param maxLength la longueur maximale
     * @return true si la longueur de l'entrée ne dépasse pas maxLength, false sinon
     */
    public static boolean isMaxLength(final String input, final int maxLength) {
        return input.length() > maxLength;
    }
}
