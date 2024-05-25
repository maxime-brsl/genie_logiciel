package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.service.ClientService;
import genieLogiciel.projet.borne.service.ReservationService;
import genieLogiciel.projet.borne.service.VehiculeService;
import genieLogiciel.projet.borne.util.LicencePlateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    Scanner scanner = new Scanner(System.in);
    @Autowired
    private ClientService clientService;

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
                    if (LicencePlateValidator.isValidLicensePlate(licensePlate)) {
                        System.out.println("Immatriculation " + licensePlate);
                        // Rechercher si l'immatriculation existe
                        if (vehiculeService.isLicensePlateInDatabase(licensePlate)) {
                            //TODO Chercher si une réservation existe
                        } else {
                            System.out.println("La plaque n'est pas reconnue.");
                            System.out.println("Entrez votre numéro de téléphone :");
                            String phoneNumber = scanner.nextLine();
                            if (clientService.isPhoneNumberInDatabase(phoneNumber)){
                                //TODO : Vérifier si une est borne disponible
                                //TODO : Proposer de réserver directement ou en différé
                            } else {
                                System.out.println("Numéro de téléphone inconnu.");
                                System.out.println("Veuillez vous inscrire : ");
                                //TODO : Procéder à l'inscription
                            }
                        }
                    } else {
                        System.out.println("Le numéro d'immatriculation n'est pas valide.");
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
}
