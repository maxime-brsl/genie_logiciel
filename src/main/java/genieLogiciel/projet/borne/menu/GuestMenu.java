package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.service.ClientService;
import genieLogiciel.projet.borne.service.ReservationService;
import genieLogiciel.projet.borne.service.VehiculeService;
import genieLogiciel.projet.borne.util.LicencePlateValidator;
import genieLogiciel.projet.borne.util.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@Service
public class GuestMenu {

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


    Scanner scanner = new Scanner(System.in);

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
                    if (handleLicensePlate()) {
                        Long vehiculeId = vehiculeService.getVehiculeIdByLicensePlate(licensePlate);
                        List<Reservation> reservations = reservationService.getReservationsByVehiculeId(vehiculeId);
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
                            System.out.println("Voulez-vous valider la présence pour cette réservation ?");
                            //TODO : Valider la présence
                        }
                    } else {
                        System.out.println("Entrez votre numéro de téléphone (avec le préfixe) :");
                        String phoneNumber = scanner.nextLine();
                        //TODO MAXIME : déplacer isValidPhoneNumber
                            if (PhoneNumberValidator.isValidPhoneNumber(phoneNumber)) {
                                //Vérifier si le numéro de téléphone est dans la base de données
                                if (clientService.isPhoneNumberInDatabase(phoneNumber)){
                                    //TODO : Vérifier si une est borne disponible
                                    //TODO : Proposer de réserver directement ou en différé
                                } else {
                                    System.out.println("Numéro de téléphone inconnu.");
                                    System.out.println("Veuillez vous inscrire : ");
                                    //TODO : Procéder à l'inscription
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

    private boolean handleLicensePlate(){
        System.out.println("Saisir le numéro d'immatriculation : ");
        String licensePlate = scanner.nextLine();
        if (LicencePlateValidator.isValidLicensePlate(licensePlate) && vehiculeService.isLicensePlateInDatabase(licensePlate)) {
            return true;
        } else {
            System.out.println("La plaque n'est pas reconnue.");
            return false;
        }
    }

}
