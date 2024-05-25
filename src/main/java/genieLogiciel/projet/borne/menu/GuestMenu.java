package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.util.LicencePlateValidator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GuestMenu {
    public void displayGuestMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vous êtes connecté en tant qu'invité");
        System.out.println("1. Entrer le numéro de réservation");
        System.out.println("2. Entrer le numéro d'immatriculation");
        System.out.println("3. Retour au menu principal");

        boolean running = true;
        while (running) {
            System.out.println("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Saisir le numéro de réservation : ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Le numéro de réservation doit être un nombre entier.");
                    } else {
                        int reservationNumber = scanner.nextInt();
                        System.out.println("Réservation numéro " + reservationNumber);
                        // Rechercher si la réservation existe
                    }
                    break;
                case "2":
                    System.out.println("Saisir le numéro d'immatriculation : ");
                    String licensePlate = scanner.nextLine();
                    if (LicencePlateValidator.isValidLicensePlate(licensePlate)) {
                        System.out.println("Immatriculation " + licensePlate);
                        // Rechercher si l'immatriculation existe
                    } else {
                        System.out.println("Le numéro d'immatriculation n'est pas valide.");
                    }
                    break;
                case "3":
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.displayMainMenu();
                    running = false;
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

}
