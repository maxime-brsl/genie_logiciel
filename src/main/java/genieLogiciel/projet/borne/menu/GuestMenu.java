package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.service.ClientService;
import genieLogiciel.projet.borne.service.ReservationService;
import genieLogiciel.projet.borne.service.VehiculeService;
import genieLogiciel.projet.borne.util.LicensePlateValidator;
import genieLogiciel.projet.borne.util.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class GuestMenu {

    Scanner scanner = new Scanner(System.in);
    @Autowired
    private MainMenu mainMenu;
    @Autowired
    private ReservationMenu reservationMenu;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private VehiculeService vehiculeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private InscriptionMenu inscriptionMenu;
    @Autowired
    private ConnectedMenu connectedMenu;
    @Autowired
    private ValidationReservationMenu validationReservationMenu;

    public void displayGuestMenu() {
        boolean running = true;
        while (running) {
            displayOptions();
            System.out.println("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Saisir le numéro de réservation : ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Le numéro de réservation doit être un nombre entier.");
                    } else {
                        int reservationId = scanner.nextInt();
                        scanner.nextLine();
                        Reservation reservation = reservationService.getReservationById(reservationId);
                        if (reservation == null) {
                            System.out.println("Aucune réservation trouvée pour le numéro " + reservationId);
                            break;
                        } else {
                            reservationMenu.displayReservationMenu(reservation);
                        }
                    }
                    break;
                case "2":
                    System.out.println("Saisir le numéro d'immatriculation : ");
                    String licensePlate = scanner.nextLine();
                    if (handleLicensePlate(licensePlate)) {
                        Long vehiculeId = vehiculeService.getVehiculeIdByLicensePlate(licensePlate);
                        List<Reservation> reservations = reservationService.getReservationsByVehiculeId(vehiculeId);
                        if (reservations.isEmpty()) {
                            System.out.println("Aucune réservation pour le véhicule " + licensePlate);
                            System.out.println("Voulez-vous vous connecter pour réserver un créneau ? (O/N)");
                            if (scanner.nextLine().equalsIgnoreCase("O")) {
                                connectedMenu.displayConnectedMenu();
                            } else {
                                System.out.println("Option invalide, veuillez réessayer.");
                                break;
                            }
                        }
                        //Vérifier si une réservation est imminente
                        Reservation imminentReservation = reservationService.getReservationImminente(reservations);
                        if (imminentReservation == null) {
                            System.out.println("Aucune réservation imminente pour le véhicule " + licensePlate);
                            //TODO : Vérifier si une borne est disponible
                            //TODO : Proposer de réserver
                            //faire un switch pour oui/non
                            //si oui, menu de réservation
                        } else {
                            //Afficher la réservation imminente
                            System.out.println(imminentReservation + "\n");
                            System.out.println("Voulez-vous valider la présence pour cette réservation ? O/N");
                            String choice2 = scanner.nextLine();
                            if (choice2.equalsIgnoreCase("O")) {
                                validationReservationMenu.displayValidateReservationMenu(imminentReservation);
                            }
                        }
                    } else {
                        System.out.println("Entrez votre numéro de téléphone (avec le préfixe) :");
                        String phoneNumber = scanner.nextLine();
                        if (PhoneNumberValidator.isValidPhoneNumber(phoneNumber)) {
                            //Vérifier si le numéro de téléphone est dans la base de données
                            if (clientService.isPhoneNumberInDatabase(phoneNumber)) {
                                //TODO : Vérifier si une est borne disponible
                                //TODO : Proposer de réserver directement ou en différé
                            } else {
                                System.out.println("Numéro de téléphone inconnu.");
                                System.out.println("Veuillez vous inscrire : ");
                                inscriptionMenu.displayInscriptionMenu();
                            }
                        } else {
                            System.out.println("Numéro de téléphone invalide.");
                            break;
                        }
                    }
                    break;
                case "3":
                    mainMenu.displayMainMenu();
                    running = false;
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private void displayOptions() {
        System.out.println("------ Menu invité ------");
        System.out.println("1. Entrer le numéro de réservation");
        System.out.println("2. Entrer le numéro d'immatriculation");
        System.out.println("3. Retour au menu principal");
    }

    private boolean handleLicensePlate(String licensePlate) {
        if (LicensePlateValidator.isValidLicensePlate(licensePlate) && vehiculeService.isLicensePlateInDatabase(licensePlate)) {
            return true;
        } else {
            System.out.println("La plaque n'est pas reconnue.");
            return false;
        }
    }

}
