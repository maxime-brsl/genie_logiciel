package genielogiciel.projet.borne.menu;

import genielogiciel.projet.borne.entity.Client;
import genielogiciel.projet.borne.service.ClientService;
import genielogiciel.projet.borne.service.VehiculeService;
import genielogiciel.projet.borne.util.CompteValidator;
import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.logging.Logger;

@Service
public class InscriptionMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(InscriptionMenu.class));
    private static ClientService clientService;
    private static VehiculeService vehiculeService;

    @Autowired
    private InscriptionMenu(ClientService clientService, VehiculeService vehiculeService) {
        InscriptionMenu.clientService = clientService;
        InscriptionMenu.vehiculeService = vehiculeService;
    }

    public static void displayInscriptionMenu() {
        boolean running = true;
        while (running) {
            displayOptions();
            LOG.info(TextMenu.CHOISIR_UNE_OPTION);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String nom = CompteValidator.saisirNom();
                    String prenom = CompteValidator.saisirPrenom();
                    String email = CompteValidator.saisirEmail();
                    String adresse = CompteValidator.saisirAdresse();
                    String numeroCarteCredit = CompteValidator.saisirNumeroCarteCredit();
                    String telephone = CompteValidator.saisirTelephone();
                    while (clientService.isPhoneNumberInDatabase(telephone)) {
                        LOG.info("Le numéro de téléphone est déjà utilisé.");
                        telephone = CompteValidator.saisirTelephone();
                    }
                    String motDePasse = CompteValidator.saisirMotDePasse();
                    Client client = new Client();
                    client.setNom(nom);
                    client.setPrenom(prenom);
                    client.setMail(email);
                    client.setAdresse(adresse);
                    client.setNumeroDebit(numeroCarteCredit);
                    client.setNumeroTel(telephone);
                    client.setMotDePasse(motDePasse);

                    clientService.addClient(client);
                    LOG.info("Inscription réussie !");
                    LOG.info("Voulez-vous ajouter une plaque d'immatriculation ? (O/N)");
                    String addLicensePlate = scanner.nextLine();
                    if (addLicensePlate.equalsIgnoreCase("O")) {
                        vehiculeService.addLicensePlateToVehicule(null, client);
                    }
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

    public static void displayOptions() {
        String menu = """
                                
                ------ Menu inscription ------
                1. Inscription
                2. %s
                """.formatted(TextMenu.RETOUR_MENU_PRINCIPAL);
        LOG.info(menu);
    }
}
