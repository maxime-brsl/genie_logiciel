package genielogiciel.projet.borne.entity;

import genielogiciel.projet.borne.enums.EtatReservation;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservation")
public class Reservation {

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "vehicule_id", nullable = false)
    private Long vehiculeId;

    @Column(name = "borne_id", nullable = false)
    private Long borneId;

    @Column(name = "facture_id")
    private Long factureId;

    @Column(name = "heure_debut", nullable = false)
    private LocalDateTime heureDebut;

    @Column(name = "heure_fin_prevue", nullable = false)
    private LocalDateTime heureFinPrevue;

    @Column(name = "heure_fin_reelle")
    private LocalDateTime heureFinReelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_res", nullable = false)
    private EtatReservation etatReservation;

    @ManyToOne
    @JoinColumn(name = "vehicule_id", insertable = false, updatable = false)
    private Vehicule vehicule;

    public Reservation(Long clientId, Long vehiculeId, Long borneId, LocalDateTime start) {
        setClientId(clientId);
        setVehiculeId(vehiculeId);
        setBorneId(borneId);
        setHeureDebut(start);
        setHeureFinP(start.plusHours(1));
        setEtatReservation(EtatReservation.EN_ATTENTE);
    }

    public Reservation() {

    }

    public String toString() {
        return """
                --------Reservation n°%d----------
                Plaque de la voiture : %s
                Numéro de la borne : %d
                Heure de debut : %s
                Heure de fin prevue : %s
                Heure de fin reelle : %s
                Etat de la reservation : %s
                """.formatted(
                id,
                vehicule != null ? vehicule.getPlaqueImmatriculation() : "Non spécifiée",
                borneId,
                heureDebut.format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
                heureFinPrevue.format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
                heureFinReelle != null ? heureFinReelle.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) : "Non spécifiée",
                etatReservation
        );
    }

    public Long getId() {
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

    public void setClientId(Long id) {
        this.clientId = id;
    }

    public EtatReservation getEtatReservation() {
        return etatReservation;
    }

    public void setEtatReservation(EtatReservation etatReservation) {
        this.etatReservation = etatReservation;
    }

    public Long getBorneId() {
        return borneId;
    }

    public void setBorneId(Long borneId) {
        this.borneId = borneId;
    }

    public LocalDateTime getHeureFinP() {
        return heureFinPrevue;
    }

    public void setHeureFinP(LocalDateTime localDateTime) {
        this.heureFinPrevue = localDateTime;
    }
}
