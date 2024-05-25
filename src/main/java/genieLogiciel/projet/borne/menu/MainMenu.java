package genieLogiciel.projet.borne.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MainMenu {

    @Autowired
    private GuestMenu guestMenu;

    Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {

        boolean running = true;
        while (running) {
            displayOptions();
            System.out.println("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
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
        System.exit(0);
    }

    public void displayOptions() {
        System.out.println("------ Menu principal ------");
        System.out.println("1. Se connecter en tant qu'invité");
        System.out.println("2. Quitter");
    }
}
