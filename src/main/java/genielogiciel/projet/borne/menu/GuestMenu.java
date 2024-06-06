package genielogiciel.projet.borne.menu;

import genielogiciel.projet.borne.entity.Reservation;
import genielogiciel.projet.borne.service.ClientService;
import genielogiciel.projet.borne.service.ReservationService;
import genielogiciel.projet.borne.service.VehiculeService;
import genielogiciel.projet.borne.util.LicensePlateValidator;
import genielogiciel.projet.borne.util.PhoneNumberValidator;
import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class GuestMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(GuestMenu.class));
    private static ReservationService reservationService;
    private static VehiculeService vehiculeService;
    private static ClientService clientService;

    @Autowired
    private GuestMenu(ReservationService reservationService, VehiculeService vehiculeService, ClientService clientService) {
        GuestMenu.reservationService = reservationService;
        GuestMenu.vehiculeService = vehiculeService;
        GuestMenu.clientService = clientService;
    }

    /**
     * Afficher le menu invité
     */
    public static void displayGuestMenu() {
        boolean running = true;
        while (running) {
            displayOptions();
            LOG.info(TextMenu.CHOISIR_UNE_OPTION);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleReservationOption();
                    break;
                case "2":
                    handleLicensePlateOption();
                    break;
                case "3":
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
     * Afficher les options du menu invité
     */
    private static void displayOptions() {
        String menu = """
                
                ------ Menu invité ------
                1. Entrer le numéro de réservation
                2. Entrer le numéro d'immatriculation
                3. %s
                """.formatted(TextMenu.RETOUR_MENU_PRINCIPAL);
        LOG.info(menu);
    }

    /**
     * Vérifier si la plaque d'immatriculation est valide et si elle est dans la base de données
     *
     * @param licensePlate plaque d'immatriculation
     * @return true si la plaque est valide et dans la base de données, false sinon
     */
    private static boolean handleLicensePlate(String licensePlate) {
        if (LicensePlateValidator.isValidLicensePlate(licensePlate) && vehiculeService.isLicenseplateExists(licensePlate)) {
            return true;
        } else {
            LOG.info("La plaque n'est pas reconnue.");
            return false;
        }
    }

    /**
     * Gérer l'option de saisie du numéro de réservation
     */
    private static void handleReservationOption() {
        LOG.info(TextMenu.SAISIR_NUMERO_RESERVATION);
        if (!scanner.hasNextInt()) {
            LOG.info(TextMenu.RESERVATION_INVALIDE);
        } else {
            int reservationId = scanner.nextInt();
            scanner.nextLine();
            Reservation reservation = reservationService.getReservationById(reservationId);
            if (reservation == null) {
                LOG.info("Aucune réservation trouvée pour le numéro " + reservationId);
            } else {
                ReservationMenu.displayReservationMenu(reservation);
            }
        }
    }

    /**
     * Gérer l'option de saisie du numéro d'immatriculation
     */
    private static void handleLicensePlateOption() {
        LOG.info(TextMenu.SAISIR_NUMERO_IMMATRICULATION);
        String licensePlate = scanner.nextLine();
        if (handleLicensePlate(licensePlate)) {
            Long vehiculeId = vehiculeService.getVehiculeIdByLicensePlate(licensePlate);
            List<Reservation> reservations = reservationService.getReservationsByVehiculeId(vehiculeId);
            if (reservations.isEmpty()) {
                LOG.info("Aucune réservation pour le véhicule {}");
                LOG.info("Voulez-vous vous connecter pour réserver un créneau ? (O/N)");
                if (scanner.nextLine().equalsIgnoreCase("O")) {
                    ConnectedMenu.displayConnectedMenu();
                } else {
                    LOG.info(TextMenu.OPTION_INVALIDE);
                }
            } else {
                checkReservationImmente(licensePlate, reservations);
            }
        } else {
            licensePlateNotFound();
        }
    }

    /**
     * Vérifier si une réservation est imminente
     *
     * @param licensePlate plaque d'immatriculation
     * @param reservations liste des réservations
     */
    private static void checkReservationImmente(String licensePlate, List<Reservation> reservations) {
        //Vérifier si une réservation est imminente
        Reservation imminentReservation = reservationService.getReservationImminente(reservations);
        if (imminentReservation == null) {
            LOG.info("Aucune réservation imminente pour le véhicule " + licensePlate);
            //TODO : Vérifier si une borne est disponible
            //TODO : Proposer de réserver
            //faire un switch pour oui/non
            //si oui, menu de réservation
        } else {
            //Afficher la réservation imminente
            LOG.info(imminentReservation + "\n");
            LOG.info("Voulez-vous valider la présence pour cette réservation ? O/N");
            String choice2 = scanner.nextLine();
            if (choice2.equalsIgnoreCase("O")) {
                ValidationReservationMenu.displayValidateReservationMenu(imminentReservation);
            }
        }
    }

    private static void licensePlateNotFound() {
        LOG.info("Entrez votre numéro de téléphone (avec le préfixe) :");
        String phoneNumber = scanner.nextLine();
        if (PhoneNumberValidator.isValidPhoneNumber(phoneNumber)) {
            //Vérifier si le numéro de téléphone est dans la base de données
            if (clientService.isPhoneNumberExists(phoneNumber)) {
                //TODO : Vérifier si une est borne disponible
                //TODO : Proposer de réserver directement ou en différé
            } else {
                LOG.info("Numéro de téléphone inconnu.");
                LOG.info("Veuillez vous inscrire : ");
                InscriptionMenu.displayInscriptionMenu();
            }
        } else {
            LOG.info("Numéro de téléphone invalide.");
        }
    }
}
