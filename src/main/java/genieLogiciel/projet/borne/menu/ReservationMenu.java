package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ReservationMenu {

    @Autowired
    private MainMenu mainMenu;

    @Autowired
    private ValidationReservationMenu validationReservationMenu;

    @Autowired
    private ReservationService reservationService;

    Scanner scanner = new Scanner(System.in);

    public void displayReservationMenu(final Reservation reservation) {
        boolean running = true;
        while (running) {
            displayOptions(reservation);
            System.out.println("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(reservation);
                    break;
                case "2":
                    if (reservationService.isReservationImminente(reservation)) {
                        validationReservationMenu.displayValidateReservationMenu(reservation);
                    } else {
                        mainMenu.displayMainMenu();
                        running = false;
                    }
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private void displayOptions(final Reservation reservation) {
        System.out.println("----- Réservation n°" + reservation.getId()+" -----");
        System.out.println("1. Voir la réservation");

        if (reservationService.isReservationImminente(reservation)) {
            System.out.println("2. Valider ma présence");
            System.out.println("3. Retour au menu principal");
        } else {
            System.out.println("2. Retour au menu principal");
        }
    }
}
