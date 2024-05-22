package genieLogiciel.projet.borne.entity;

import jakarta.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String numeroCarteBancaire;
    private String telephone;

    // Getters et setters
    public Long getId() {
            return id;
        }
    public void setId(final Long id) {
            this.id = id;
        }
    public String getNom() {
            return nom;
        }
    public void setNom(final String nom) {
            this.nom = nom;
        }

    public String getEmail() {
            return email;
        }

    public void setEmail(final String email) {
            this.email = email;
    }
}
