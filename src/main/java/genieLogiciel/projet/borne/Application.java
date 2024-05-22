package genieLogiciel.projet.borne;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class Application {
    @Autowired
    private ClientService clientService;

    public void runTerminalApp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans l'application terminal !");
        System.out.println("1. Afficher tous les clients");
        System.out.println("2. Ajouter un nouveau client");
        System.out.println("3. Quitter");

        boolean running = true;
        while (running) {
            System.out.print("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Action : Afficher tous les clients");
                    for (Client client : clientService.getAllClients()) {
                        System.out.println(client.getNom());
                    }

                    break;
                case "2":
                    System.out.println("Action : Ajouter un nouveau client");
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
    }
}
