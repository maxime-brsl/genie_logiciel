package genieLogiciel.projet.borne.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client") // Spécifie le nom de la table dans la base de données
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client") // Spécifie le nom de la colonne dans la table
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "mail")
    private String mail;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "numero_debit")
    private String numeroDebit;

    @Column(name = "numero_tel")
    private String numeroTel;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    public Client(String nom, String prenom, String email, String adresse, String numeroCarteCredit, String telephone, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = email;
        this.adresse = adresse;
        this.numeroDebit = numeroCarteCredit;
        this.numeroTel = telephone;
        this.motDePasse = motDePasse;
    }

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

    public String getMail() {
        return mail;
    }

    public void setMail(final String email) {
        this.mail = email;
    }

    // Les autres getters et setters

}
