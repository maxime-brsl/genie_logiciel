package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Borne;
import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.enums.EtatBorne;
import genieLogiciel.projet.borne.enums.EtatReservation;
import genieLogiciel.projet.borne.service.BorneService;
import genieLogiciel.projet.borne.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ValidationReservationMenu {

    @Autowired
    private MainMenu mainMenu;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BorneService borneService;

    Scanner scanner = new Scanner(System.in);

    public void displayValidateReservationMenu(Reservation reservation) {
        boolean running = true;
        while (running) {
            displayOptions();
            System.out.println("Choisissez une option : ");
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
                    System.out.println("Votre présence a été validée.");
                    mainMenu.displayMainMenu();
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
        System.out.println("------ Validation réservation ------");
        System.out.println("1. Valider ma présence");
        System.out.println("2. Retour au menu principal");
    }
}
