package genielogiciel.projet.borne;

import genielogiciel.projet.borne.menu.MainMenu;
import org.springframework.stereotype.Service;

@Service
public class Application {

    public void run() {
        MainMenu.displayMainMenu();
    }
}
