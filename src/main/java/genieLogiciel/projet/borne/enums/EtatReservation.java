package genieLogiciel.projet.borne.enums;

public enum EtatReservation {
    EN_ATTENTE("en attente"),
    EN_COURS("en cours"),
    TERMINEE("terminée");

    private final String value;

    EtatReservation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}