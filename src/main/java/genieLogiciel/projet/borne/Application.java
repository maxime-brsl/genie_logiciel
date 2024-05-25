package genieLogiciel.projet.borne;

import genieLogiciel.projet.borne.menu.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Application implements CommandLineRunner {
    private final MainMenu mainMenu;
    @Autowired
    public Application(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }


    @Override
    public void run(String... args) {
        mainMenu.displayMainMenu();
    }
}
