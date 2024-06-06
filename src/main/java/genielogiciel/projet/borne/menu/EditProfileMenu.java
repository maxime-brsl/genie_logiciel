package genielogiciel.projet.borne.menu;

import genielogiciel.projet.borne.entity.Client;
import genielogiciel.projet.borne.service.ClientService;
import genielogiciel.projet.borne.util.CompteValidator;
import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class EditProfileMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(ConnectedMenu.class));
    private static ClientService clientService;

    @Autowired
    private EditProfileMenu(ClientService clientService) {
        EditProfileMenu.clientService = clientService;
    }

    /**
     * Afficher le menu de modification du profil
     *
     * @param client
     */
    public static void displayEditProfileMenu(final Client client) {
        boolean running = true;
        while (running) {
            displayOptions();
            LOG.info(TextMenu.CHOISIR_UNE_OPTION);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayOptionsEditProfile(client.getPrenom(), client.getNom());
                    running = choiceOptionsEditProfile(scanner.nextLine(), client);

                    break;
                case "2":
                    MainMenu.displayMainMenu();
                    running = false;
                    break;
                default:
                    LOG.info(TextMenu.OPTION_INVALIDE);
            }
        }
        scanner.close();
    }

    /**
     * Afficher les options du menu de modification
     */
    private static void displayOptions() {
        String menu = """
                                
                ------ Menu de modification ------
                1. Modifier mon profil
                2. Retour menu principal
                """;
        LOG.info(menu);
    }

    /**
     * Afficher les options de modification du profil
     *
     * @param prenom prénom du client
     * @param nom    nom du client
     */
    private static void displayOptionsEditProfile(final String prenom, final String nom) {
        String menu = """
                ------ Bienvenue %s %s ! ------
                Que voulez-vous modifier ?
                1. Nom
                2. Prénom
                3. Adresse-mail
                4. Adresse postale
                5. Numéro de débit
                6. Numéro de téléphone
                7. Mot de passe
                8. Retour menu précédent
                """.formatted(prenom, nom);
        LOG.info(menu);
    }

    /**
     * Choisir les options de modification du profil
     *
     * @param choice choix de l'utilisateur
     * @param client client connecté
     * @return retourne true si l'utilisateur veut continuer à modifier son profil, false sinon
     */
    private static boolean choiceOptionsEditProfile(final String choice, final Client client) {
        switch (choice) {
            case "1":
                String nom = CompteValidator.saisirNom();
                client.setNom(nom);
                clientService.updateClient(client);
                LOG.info("Nom modifié !");
                LOG.info(client.toString());
                break;

            case "2":
                String prenom = CompteValidator.saisirPrenom();
                client.setPrenom(prenom);
                clientService.updateClient(client);
                LOG.info("Prénom modifié !");
                LOG.info(client.toString());
                break;

            case "3":
                String mail = CompteValidator.saisirEmail();
                client.setMail(mail);
                clientService.updateClient(client);
                LOG.info("Adresse-mail modifiée !");
                LOG.info(client.toString());
                break;

            case "4":
                String adresse = CompteValidator.saisirAdresse();
                client.setAdresse(adresse);
                clientService.updateClient(client);
                LOG.info("Adresse postale modifiée !");
                LOG.info(client.toString());
                break;

            case "5":
                String numeroDebit = CompteValidator.saisirNumeroCarteCredit();
                client.setNumeroDebit(numeroDebit);
                clientService.updateClient(client);
                LOG.info("Numéro de débit modifié !");
                LOG.info(client.toString());
                break;

            case "6":
                String numeroTelephone = CompteValidator.saisirTelephone();
                client.setNumeroTelephone(numeroTelephone);
                clientService.updateClient(client);
                LOG.info("Numéro de téléphone modifié !");
                LOG.info(client.toString());
                break;

            case "7":
                String motDePasse = CompteValidator.saisirMotDePasse();
                client.setMotDePasse(motDePasse);
                clientService.updateClient(client);
                LOG.info("Mot de passe modifié !");
                LOG.info(client.toString());
                break;

            case "8":
                ConnectedMenu.displayConnectedMenu();
                return false;

            default:
                LOG.info(TextMenu.OPTION_INVALIDE);
        }
        return true;
    }

}