package genielogiciel.projet.borne.menu;

import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.logging.Logger;

@Service
public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(MainMenu.class));

    MainMenu() {
    }

    public static void displayMainMenu() {
        boolean running = true;
        while (running) {
            displayOptions();
            LOG.info(TextMenu.CHOISIR_UNE_OPTION);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    GuestMenu.displayGuestMenu();
                    break;
                case "2":
                    ConnectedMenu.displayConnectedMenu();
                    break;
                case "3":
                    LOG.info("Merci d'avoir utilisé nos bornes !");
                    running = false;
                    break;
                default:
                    LOG.info(TextMenu.OPTION_INVALIDE);
            }
        }
        scanner.close();
        System.exit(0);
    }

    public static void displayOptions() {
        String menu = """

                ------ Menu principal ------
                1. Se connecter en tant qu'invité
                2. Se connecter
                3. Quitter
                """;
        LOG.info(menu);
    }
}
