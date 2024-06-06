package genielogiciel.projet.borne.enums;

/**
 * Enumération des états possibles d'une Borne
 */
public enum EtatBorne {
    DISPONIBLE("DISPONIBLE"),
    INDISPONIBLE("INDISPONIBLE"),
    OCCUPEE("OCCUPEE"),
    RESERVEE("RESERVEE");
    private final String value;

    EtatBorne(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
