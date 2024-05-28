package genieLogiciel.projet.borne.util;

import genieLogiciel.projet.borne.menu.MainMenu;

public class MenuUtil {


    public static boolean menuPrincipal() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.displayMainMenu();
        return false;
    }
}
