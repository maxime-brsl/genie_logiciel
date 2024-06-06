package genielogiciel.projet.borne.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicule")
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicule")
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "plaque_immatriculation", nullable = false, length = 50)
    private String plaqueImmatriculation;

    @Column(name = "loue", nullable = false)
    private Boolean estLoue;

    public String getPlaqueImmatriculation() {
        return plaqueImmatriculation;
    }

    public void setPlaqueImmatriculation(final String plaqueImmatriculation) {
        this.plaqueImmatriculation = plaqueImmatriculation;
    }

    public long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setClientId(final Long clientId) {
        this.clientId = clientId;
    }

    public void setEstLoue(final Boolean loue) {
        this.estLoue = loue;
    }
}
