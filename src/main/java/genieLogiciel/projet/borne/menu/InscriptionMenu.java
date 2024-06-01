package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.service.ClientService;
import genieLogiciel.projet.borne.service.VehiculeService;
import genieLogiciel.projet.borne.util.CompteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InscriptionMenu {

    Scanner scanner = new Scanner(System.in);
    @Autowired
    private MainMenu mainMenu;
    @Autowired
    private ClientService clientService;
    @Autowired
    private VehiculeService vehiculeService;

    public void displayInscriptionMenu() {
        boolean running = true;
        while (running) {
            displayOptions();
            System.out.println("Choisissez une option : ");
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
                        System.out.println("Le numéro de téléphone est déjà utilisé.");
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
                    System.out.println("Inscription réussie !");
                    System.out.println("Voulez-vous ajouter une plaque d'immatriculation ? (O/N)");
                    String addLicensePlate = scanner.nextLine();
                    if (addLicensePlate.equalsIgnoreCase("O")) {
                        vehiculeService.addLicensePlateToVehicule(null, client);
                    }
                    break;
                case "2":
                    mainMenu.displayMainMenu();
                    running = false;
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

    public void displayOptions() {
        System.out.println("------ Menu inscription ------");
        System.out.println("1. Inscription");
        System.out.println("2. Retour au menu principal");
    }
}
