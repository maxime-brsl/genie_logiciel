package genieLogiciel.projet.borne.entity;

import genieLogiciel.projet.borne.enums.EtatBorne;
import jakarta.persistence.*;

@Entity
@Table(name = "borne")
public class Borne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_borne")
    private long id;

    @Column(name = "reservation_id", nullable = false)
    private Integer reservationId;

    @Column(name = "etat_borne", nullable = false)
    private EtatBorne etatBorne;

    public long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public EtatBorne getEtatBorne() {
        return etatBorne;
    }

    public void setEtatBorne(final EtatBorne etatBorne) {
        this.etatBorne = etatBorne;
    }

}