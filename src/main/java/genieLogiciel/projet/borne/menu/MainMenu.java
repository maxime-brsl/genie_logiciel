package genieLogiciel.projet.borne.menu;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue sur nos bornes !");
        System.out.println("1. Se connecter en tant qu'invité");
        System.out.println("2. Quitter");

        boolean running = true;
        while (running) {
            System.out.println("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    GuestMenu guestMenu = new GuestMenu();
                    guestMenu.displayGuestMenu();
                    break;
                case "2":
                    System.out.println("Merci d'avoir utilisé nos borne !");
                    running = false;
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }
}
