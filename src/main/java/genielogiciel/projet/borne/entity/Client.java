package genielogiciel.projet.borne.entity;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public String toString() {
        return """
                                
                --------Client n°%d----------
                Nom : %s
                Prénom : %s
                Mail : %s
                Adresse : %s
                Numéro de débit : XXXX-XXXX-XXXX
                Numéro de téléphone : %s
                """.formatted(id, nom, prenom, mail, adresse, numeroTel);
    }


    public void setAdresse(final String adresse) {
        this.adresse = adresse;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public void setNumeroTel(final String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(final String motDePasse) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.motDePasse = passwordEncoder.encode(motDePasse);
    }

    public String getNumeroDebit() {
        return numeroDebit;
    }

    public void setNumeroDebit(final String numeroDebit) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.numeroDebit = encoder.encode(numeroDebit);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

}