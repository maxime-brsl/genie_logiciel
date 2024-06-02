package genielogiciel.projet.borne.menu;

import genielogiciel.projet.borne.entity.Reservation;
import genielogiciel.projet.borne.service.ReservationService;
import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReservationMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(ReservationMenu.class));
    private static ReservationService reservationService;

    @Autowired
    private ReservationMenu(ReservationService reservationService) {
        ReservationMenu.reservationService = reservationService;
    }

    public static void displayReservationMenu(final Reservation reservation) {
        boolean running = true;
        while (running) {
            displayOptions(reservation);
            LOG.info(TextMenu.CHOISIR_UNE_OPTION);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (LOG.isLoggable(Level.INFO)) {
                        LOG.info(reservation.toString());
                    }
                    break;
                case "2":
                    if (reservationService.isReservationImminente(reservation)) {
                        ValidationReservationMenu.displayValidateReservationMenu(reservation);
                    } else {
                        MainMenu.displayMainMenu();
                        running = false;
                    }
                    break;
                default:
                    LOG.info(TextMenu.OPTION_INVALIDE);
            }
        }
        scanner.close();
    }

    private static void displayOptions(final Reservation reservation) {
        StringBuilder menu = new StringBuilder();
        menu.append("\n----- Réservation n°").append(reservation.getId()).append(" -----\n")
                .append("1. Voir la réservation\n");

        if (reservationService.isReservationImminente(reservation)) {
            menu.append("2. Valider ma présence\n")
                    .append("3. ").append(TextMenu.RETOUR_MENU_PRINCIPAL);
        } else {
            menu.append("2. ").append(TextMenu.RETOUR_MENU_PRINCIPAL);
        }
        if (LOG.isLoggable(Level.INFO)) {
            LOG.info(menu.toString());
        }
    }
}
