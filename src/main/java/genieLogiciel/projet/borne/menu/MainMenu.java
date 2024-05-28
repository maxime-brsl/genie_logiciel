package genieLogiciel.projet.borne.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MainMenu {

    @Autowired
    private GuestMenu guestMenu;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    Scanner scanner = new Scanner(System.in);
    @Autowired
    private ConnectedMenu connectedMenu;

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
                    connectedMenu.displayConnectedMenu();
                    break;
                case "3":
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
        System.out.println("2. Se connecter");
        System.out.println("3. Quitter");
    }
}
