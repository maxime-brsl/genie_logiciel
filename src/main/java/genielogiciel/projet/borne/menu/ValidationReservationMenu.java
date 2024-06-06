package genielogiciel.projet.borne.menu;

import genielogiciel.projet.borne.entity.Reservation;
import genielogiciel.projet.borne.enums.EtatBorne;
import genielogiciel.projet.borne.enums.EtatReservation;
import genielogiciel.projet.borne.service.BorneService;
import genielogiciel.projet.borne.service.ReservationService;
import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Menu de validation de réservation
 */
@Component
public class ValidationReservationMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(ValidationReservationMenu.class));
    private static ReservationService reservationService;
    private static BorneService borneService;

    @Autowired
    private ValidationReservationMenu(ReservationService reservationService, BorneService borneService) {
        ValidationReservationMenu.reservationService = reservationService;
        ValidationReservationMenu.borneService = borneService;
    }

    /**
     * Afficher le menu de validation de réservation
     *
     * @param reservation la réservation à valider
     */
    public static void displayValidateReservationMenu(Reservation reservation) {
        boolean running = true;
        while (running) {
            displayOptions();
            LOG.info(TextMenu.CHOISIR_UNE_OPTION);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    reservationService.changeReservationState(reservation, EtatReservation.EN_COURS);
                    boolean periodeAttente = reservationService.isPeriodAttente(reservation);
                    if (periodeAttente) {
                        //TODO US-012
                    }
                    long idBorne = reservation.getBorneId();
                    borneService.changeBorneStateWithId(idBorne, EtatBorne.OCCUPEE);
                    LOG.info("Votre présence a été validée.");
                    MainMenu.displayMainMenu();
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
     * Afficher les options du menu de validation de réservation
     */
    public static void displayOptions() {
        String menu = """
                
                 ------ Validation réservation ------
                 1. Valider ma présence
                 2.  %s
                \s""".formatted(TextMenu.RETOUR_MENU_PRINCIPAL);
        LOG.info(menu);
    }
}
