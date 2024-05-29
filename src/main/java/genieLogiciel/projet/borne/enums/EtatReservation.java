package genieLogiciel.projet.borne.enums;

/**
 * Enumération des états possibles d'une Borne
 */
public enum EtatReservation {
    EN_ATTENTE("EN_ATTENTE"),
    EN_COURS("EN_COURS"),
    TERMINEE("TERMINEE"),;

    private final String value;

    EtatReservation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}