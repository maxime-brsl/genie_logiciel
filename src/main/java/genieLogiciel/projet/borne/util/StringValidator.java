package genieLogiciel.projet.borne.util;

/**
 * Classe de validation des chaînes de caractères
 */

public class StringValidator {

    private static final int MAX_LENGTH_NOM = 50;
    private static final String REGEX_MAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String REGEX_NOM = "[a-zA-Z-]+";
    private static final String REGEX_ADRESSE = "^[0-9]+[a-zA-Z]?(?:TER|BIS)? [\\p{L}0-9' -]+, [0-9]{5} [\\p{L} -]+, [\\p{L} -]+$";
    private static final String REGEX_CARTE_CREDIT = "^\\d{16}\\s\\d{3}\\s(0[1-9]|1[0-2])/\\d{2}$";
    private static final String REGEX_MOT_DE_PASSE = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.;,:!?*])(?=\\S+$).{8,}$";


    /**
     * Vérifie si l'entrée est un nom valide
     *
     * @param input le nom à vérifier
     * @return true si le nom est valide, false sinon
    */
    public static boolean isNom(final String input) {
        return input != null && input.matches(REGEX_NOM) && !isMaxLength(input, MAX_LENGTH_NOM);
    }

    /**
     * Vérifie si l'entrée est un prénom valide
     *
     * @param input le prénom à vérifier
     * @return true si le prénom est valide, false sinon
     */
    public static boolean isEmail(final String input) {
        return input != null && input.matches(REGEX_MAIL);
    }

    /**
     * Vérifie si l'entrée est une adresse valide
     *
     * @param input l'adresse à vérifier
     * @return true si l'adresse est valide, false sinon
     */
    public static boolean isAdresse(final String input) {
        return input != null && input.matches(REGEX_ADRESSE);
    }

    /**
     * Vérifie si l'entrée est un numéro de carte de crédit valide
     *
     * @param input le numéro de carte de crédit à vérifier
     * @return true si le numéro de carte de crédit est valide, false sinon
     */
    public static boolean isCarteCredit(final String input) {
        return input != null && input.matches(REGEX_CARTE_CREDIT);
    }

    /**
     * Vérifie si l'entrée est un mot de passe valide
     *
     * @param input le mot de passe à vérifier
     * @return true si le mot de passe est valide, false sinon
     */
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
