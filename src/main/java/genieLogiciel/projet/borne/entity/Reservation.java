package genieLogiciel.projet.borne.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    @Column(name = "vehicule_id", nullable = false)
    private Long vehiculeId;

    @Column(name = "borne_id", nullable = false)
    private Integer borneId;

    @Column(name = "facture_id")
    private Integer factureId;

    @Column(name = "heure_debut", nullable = false)
    private LocalDateTime heureDebut;

    @Column(name = "heure_fin_prevue", nullable = false)
    private LocalDateTime heureFinPrevue;

    @Column(name = "heure_fin_reelle")
    private LocalDateTime heureFinReelle;

    @Column(name = "etat_res", nullable = false)
    private String etatReservation;

    @ManyToOne
    @JoinColumn(name = "vehicule_id", insertable = false, updatable = false)
    private Vehicule vehicule;

    public String toString() {
        return "--------Reservation n°" + id + "----------" +
                "\nPlaque de la voiture : " + (vehicule != null ? vehicule.getPlaqueImmatriculation() : "Non spécifiée") +
                "\nNuméro de la borne : " + borneId +
                "\nHeure de debut : " + heureDebut.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                "\nHeure de fin prevue : " + heureFinPrevue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                "\nHeure de fin reelle : " + (heureFinReelle != null ? heureFinReelle.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "Non spécifiée") +
                "\nEtat de la reservation : " + etatReservation;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(final LocalDateTime localDateTime) {
        this.heureDebut = localDateTime;
    }

    public void setVehiculeId(final Long vehiculeId) {
        this.vehiculeId = vehiculeId;
    }
}
