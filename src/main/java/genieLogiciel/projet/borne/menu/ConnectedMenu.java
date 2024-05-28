package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.entity.Vehicule;
import genieLogiciel.projet.borne.service.ClientService;
import genieLogiciel.projet.borne.service.ReservationService;
import genieLogiciel.projet.borne.service.VehiculeService;
import genieLogiciel.projet.borne.util.CompteValidator;
import genieLogiciel.projet.borne.util.LicencePlateValidator;
import genieLogiciel.projet.borne.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConnectedMenu {

    Scanner scanner = new Scanner(System.in);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private ClientService clientService;
    @Autowired
    private VehiculeService vehiculeService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private MainMenu mainMenu;
    @Autowired
    private InscriptionMenu inscriptionMenu;
    @Autowired
    private ReservationMenu reservationMenu;

    public void displayConnectedMenu() {
        boolean running = true;
        while (running) {
            displayOptions();
            System.out.println("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String numeroTelephone = CompteValidator.saisirTelephone();
                    String motDePasse = CompteValidator.saisirMotDePasse();
                    boolean test = clientService.verifierMotDePasse(numeroTelephone, motDePasse);
                    if (test) {
                        Client client = clientService.getClientByPhoneNumber(numeroTelephone);
                        System.out.println("Connexion réussie !");
                        displayOptionsConnected(client.getPrenom(), client.getNom());
                        running = choiceOptionsConnected(scanner.nextLine(), client);
                    } else {
                        System.out.println("Identifiants incorrects, voulez-vous vous inscrire ? (O/N)");
                        String inscription = scanner.nextLine();
                        if (inscription.equalsIgnoreCase("O")) {
                            inscriptionMenu.displayInscriptionMenu();
                        }
                    }
                    break;
                case "2":
                    running = MenuUtil.menuPrincipal();
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private void displayOptions() {
        System.out.println("------ Menu de connexion ------");
        System.out.println("1. Se connecter");
        System.out.println("2. Retour menu principal");
    }

    private void displayOptionsConnected(final String prenom, final String nom) {
        System.out.println("------ Bienvenue " + prenom + " " + nom + " ! ------");
        System.out.println("1. Ajouter une plaque d'immatriculation");
        System.out.println("2. Consulter mon profil");
        System.out.println("3. Modifier mon profil");
        System.out.println("4. Accéder à ma réservation");
        System.out.println("5. Consulter mes réservations");
        System.out.println("6. Réserver un créneau");
        System.out.println("7. Retour menu principal");
    }

    private boolean choiceOptionsConnected(final String choice, final Client client) {
        switch (choice) {
            case "1":
                Vehicule vehicule = new Vehicule();
                System.out.println("Saisir le numéro d'immatriculation : ");
                String licensePlate = scanner.nextLine();
                while (!LicencePlateValidator.isValidLicensePlate(licensePlate)) {
                    System.out.println("Le numéro d'immatriculation n'est pas valide.");
                    System.out.println("Saisir le numéro d'immatriculation : ");
                    licensePlate = scanner.nextLine();
                }
                vehicule.setPlaqueImmatriculation(licensePlate);
                long idClient = client.getId();
                vehicule.setClientId(idClient);
                vehicule.setLoue(false);
                vehiculeService.addVehicule(vehicule);
                break;
            case "2":
                // TODO US-009 JULIE
                break;
            case "3":
                // TODO US-010
                break;
            case "4":
                System.out.println("Saisir le numéro de réservation : ");
                int idReservation = scanner.nextInt();
                Reservation reservation = reservationService.getReservationById(idReservation);
                reservationMenu.displayReservationMenu(reservation);
                break;
            case "5":
                // TODO US-018
                break;
            case "6":
                // TODO US-007
                break;
            case "7":
                mainMenu.displayMainMenu();
                return false;
            default:
                System.out.println("Option invalide, veuillez réessayer.");
        }
        return true;
    }
}
