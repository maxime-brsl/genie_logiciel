package genieLogiciel.projet.borne;

import genieLogiciel.projet.borne.menu.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Application {

    private final MainMenu mainMenu;

    public Application(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void run() {
        mainMenu.displayMainMenu();
    }
}
