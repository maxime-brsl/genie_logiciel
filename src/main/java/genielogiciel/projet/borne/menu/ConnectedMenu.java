package genielogiciel.projet.borne.menu;

import genielogiciel.projet.borne.entity.Client;
import genielogiciel.projet.borne.entity.Reservation;
import genielogiciel.projet.borne.entity.Vehicule;
import genielogiciel.projet.borne.service.ClientService;
import genielogiciel.projet.borne.service.ReservationService;
import genielogiciel.projet.borne.service.VehiculeService;
import genielogiciel.projet.borne.util.CompteValidator;
import genielogiciel.projet.borne.util.LicensePlateValidator;
import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ConnectedMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(ConnectedMenu.class));
    private static ClientService clientService;
    private static VehiculeService vehiculeService;
    private static ReservationService reservationService;

    @Autowired
    private ConnectedMenu(ClientService clientService, VehiculeService vehiculeService, ReservationService reservationService) {
        ConnectedMenu.clientService = clientService;
        ConnectedMenu.vehiculeService = vehiculeService;
        ConnectedMenu.reservationService = reservationService;
    }

    /**
     * Afficher le menu de connexion
     */
    public static void displayConnectedMenu() {
        boolean running = true;
        while (running) {
            displayOptions();
            LOG.info(TextMenu.CHOISIR_UNE_OPTION);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String numeroTelephone = CompteValidator.saisirTelephone();
                    String motDePasse = CompteValidator.saisirMotDePasse();
                    boolean motDePasseValide = clientService.verifierMotDePasse(numeroTelephone, motDePasse);
                    if (motDePasseValide) {
                        Client client = clientService.getClientByPhoneNumber(numeroTelephone);
                        LOG.info("Connexion réussie !");
                        displayOptionsConnected(client.getPrenom(), client.getNom());
                        running = choiceOptionsConnected(scanner.nextLine(), client);
                    } else {
                        LOG.info("Identifiants incorrects, voulez-vous vous inscrire ? (O/N)");
                        String inscription = scanner.nextLine();
                        if (inscription.equalsIgnoreCase("O")) {
                            InscriptionMenu.displayInscriptionMenu();
                        }
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

    /**
     * Afficher les options du menu de connexion
     */
    private static void displayOptions() {
        String menu = """
                
                ------ Menu de connexion ------
                1. Se connecter
                2. Retour menu principal
                """;
        LOG.info(menu);
    }

    /**
     * Afficher les options du menu connecté
     *
     * @param prenom prénom du client
     * @param nom    nom du client
     */
    private static void displayOptionsConnected(final String prenom, final String nom) {
        String menu = """
                ------ Bienvenue %s %s ! ------
                1. Ajouter une plaque d'immatriculation
                2. Consulter mon profil
                3. Modifier mon profil
                4. Accéder à ma réservation avec mon numéro de réservation
                5. Consulter mes réservations
                6. Réserver un créneau
                7. Retour menu principal
                """.formatted(prenom, nom);
        LOG.info(menu);
    }


    /**
     * Gérer les options du menu connecté
     *
     * @param choice choix de l'utilisateur
     * @param client client connecté
     * @return true si l'utilisateur reste connecté, false sinon
     */
    private static boolean choiceOptionsConnected(final String choice, final Client client) {
        switch (choice) {
            case "1":
                Vehicule vehicule = new Vehicule();
                LOG.info(TextMenu.SAISIR_NUMERO_IMMATRICULATION);
                String licensePlate = scanner.nextLine();
                while (!LicensePlateValidator.isValidLicensePlate(licensePlate)) {
                    LOG.info(TextMenu.SAISIR_NUMERO_IMMATRICULATION);
                    licensePlate = scanner.nextLine();
                }
                vehicule.setPlaqueImmatriculation(licensePlate);
                long idClient = client.getId();
                vehicule.setClientId(idClient);
                vehicule.setEstLoue(false);
                vehiculeService.addVehicule(vehicule);
                break;
            case "2":
                if (LOG.isLoggable(Level.INFO)) {
                    LOG.info(client.toString());
                }
                break;
            case "3":
                // TODO US-010
                break;
            case "4":
                LOG.info(TextMenu.SAISIR_NUMERO_RESERVATION);
                if (!scanner.hasNextInt()) {
                    LOG.info(TextMenu.RESERVATION_INVALIDE);
                } else {
                    int reservationId = scanner.nextInt();
                    scanner.nextLine();
                    Reservation reservation = reservationService.getReservationById(reservationId);
                    ReservationMenu.displayReservationMenu(reservation);
                }
                break;
            case "5":
                List<Reservation> reservations = reservationService.getReservationsByClientId(client.getId());
                if (reservations.isEmpty()) {
                    LOG.info("Vous n'avez aucune réservation.");
                } else {
                    for (Reservation reservation : reservations) {
                        LOG.info(reservation.toString());
                    }
                }
                break;
            case "6":
                CreateReservationMenu.displayCreateReservationMenu(client);
                break;
            case "7":
                MainMenu.displayMainMenu();
                return false;
            default:
                LOG.info(TextMenu.OPTION_INVALIDE);
        }
        return true;
    }
}
