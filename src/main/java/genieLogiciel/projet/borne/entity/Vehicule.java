package genieLogiciel.projet.borne.entity;

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
    private Boolean loue;

    public String getPlaqueImmatriculation() {
        return plaqueImmatriculation;
    }

    public Long getId() {
        return id;
    }

    public void setPlaqueImmatriculation(String plaqueImmatriculation) {
        this.plaqueImmatriculation = plaqueImmatriculation;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setLoue(Boolean loue) {
        this.loue = loue;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
