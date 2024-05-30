package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Borne;
import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.service.BorneService;
import genieLogiciel.projet.borne.service.ReservationService;
import genieLogiciel.projet.borne.service.VehiculeService;
import genieLogiciel.projet.borne.util.LicensePlateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class CreateReservationMenu {

    Scanner scanner = new Scanner(System.in);

    @Autowired
    private MainMenu mainMenu;

    @Autowired
    private BorneService borneService;

    @Autowired
    private VehiculeService vehiculeService;

    @Autowired
    private ReservationService reservationService;

    public void displayCreateReservationMenu(final Client client) {
        List<Borne> bornesTmp = borneService.getAllBornes();
        List<Long> bornes = new ArrayList<>();
        for (Borne borne : bornesTmp) {
            bornes.add(borne.getId());
        }
        boolean running = true;
        while (running) {
            displayOptions();
            System.out.println("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    LocalDateTime chosenDateStart = chooseTimeSlot();
                    Map<LocalDateTime, List<Long>> creneaux = borneService.findAvailableDates(chosenDateStart, chosenDateStart.plusHours(12), bornes);

                    chooseReservation(creneaux, client);
                    break;
                case "2":
                    mainMenu.displayMainMenu();
                    running = false;
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private void displayOptions() {
        System.out.println("\n----- Réservation -----");
        System.out.println("1. Faire une réservation");
        System.out.println("2. Retour au menu principal");
    }

    /**
     * Choisir un créneau horaire pour la réservation
     * on affiche les jours à la semaine et on divise en deux par plage horaire pour limiter l'affichage
     */
    public LocalDateTime chooseTimeSlot() {
        LocalDateTime today = LocalDateTime.now();

        // Affiche les 7 jours à venir à partir d'aujourd'hui
        System.out.println("Veuillez choisir quel jour vous souhaitez faire une réservation :");
        for (int i = 0; i <= 6; i++) {
            LocalDateTime futureDate = today.plusDays(i);
            System.out.println(i + ". " + futureDate.format(DateTimeFormatter.ofPattern("EEEE dd MMM")));
        }
        System.out.println("7. Semaine suivante");
        System.out.print("Entrez un nombre (0-7): ");
        int day = scanner.nextInt();
        while (day == 7) {
            today = today.plusDays(7);
            System.out.println("Semaine suivante :");
            System.out.println("Veuillez choisir quel jour vous souhaitez faire une réservation :");
            for (int i = 0; i <= 6; i++) {
                LocalDateTime futureDate = today.plusDays(i);
                System.out.println(i + ". " + futureDate.format(DateTimeFormatter.ofPattern("EEEE dd MMM")));
            }
            day = day + scanner.nextInt();
        }

        System.out.println("Voulez-vous réserver pour le matin (de 00h à 12h) ou l'après-midi (de 12h à 00h) ? (1/2)");
        long period = scanner.nextLong();
        period = period - 1;

        LocalDateTime chosenDate = today.plusDays(day).with(LocalTime.MIDNIGHT);
        chosenDate = chosenDate.plusHours(12 * period);
        return chosenDate;
    }

    /**
     * Choisir une réservation parmi les créneaux disponibles
     *
     * @param creneaux Map des créneaux disponibles : date + bornes dispo à cette date
     * @param client   Client qui fait la réservation
     */
    public void chooseReservation(Map<LocalDateTime, List<Long>> creneaux, Client client) {
        List<Integer> impossible = new ArrayList<>();
        if (creneaux.isEmpty()) {
            System.out.println("Aucune borne disponible pour cette plage horaire.");
            return;
        }
        Map<LocalDateTime, List<Long>> sortedCreneaux = new TreeMap<>(creneaux);
        List<Map.Entry<LocalDateTime, List<Long>>> creneauxList = new ArrayList<>(sortedCreneaux.entrySet());
        for (int i = 0; i < creneauxList.size(); i++) {
            Map.Entry<LocalDateTime, List<Long>> entry = creneauxList.get(i);
            System.out.println(i + ".");
            System.out.println("Créneau : " + entry.getKey().format(DateTimeFormatter.ofPattern("EEEE dd MMM HH:mm")));
            if (entry.getValue().isEmpty()) {
                System.out.println("Aucune borne disponible.");
                impossible.add(i);
            } else {
                System.out.println("Bornes disponibles : " + entry.getValue());
            }
        }

        System.out.println("Entrez le nombre du créneau que vous souhaitez réserver : ");
        System.out.println("Entrez un autre chiffre pour annuler.");
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            if (!(choice >= 0 && choice < creneauxList.size())) {
                return;
            }
            if (impossible.contains(choice)) {
                System.out.println("Créneau impossible à réserver.");
                return;
            }
        } else {
            scanner.next();
            return;
        }
        Map.Entry<LocalDateTime, List<Long>> chosenEntry = creneauxList.get(choice);
        String LicensePlate = inputLicensePlate(client);
        Reservation reservation = addReservationWithLicencePlate(client, LicensePlate, chosenEntry);
        reservationService.addReservation(reservation);
        System.out.println("Créneau réservé avec succès !");
        System.out.println(reservation);
        reserveAdditionalHour(reservation);
    }

    private void reserveAdditionalHour(Reservation reservation) {
        System.out.println("Voulez-vous réserver une heure supplémentaire ? (O/N)");
        while (scanner.nextLine().equalsIgnoreCase("O")) {
            System.out.println("Nous regardons si la borne est disponible pour le créneau suivant.");
            if (borneService.hasReservationAtCurrentTime(reservation.getBorneId(), reservation.getHeureFinP().plusHours(1))) {
                System.out.println("Aucune borne disponible pour le créneau suivant.");
                return;
            } else {
                reservation.setHeureFinP(reservation.getHeureFinP().plusHours(1));
                System.out.println("Créneau réservé avec succès !");
                System.out.println(reservation);
            }
            System.out.println("Voulez-vous réserver une heure supplémentaire ? (O/N)");
        }
    }

    private String inputLicensePlate(Client client) {
        System.out.println("Saisir le numéro d'immatriculation pour la réservation : ");
        scanner.nextLine();
        String licensePlate = scanner.nextLine();

        while (!LicensePlateValidator.isValidLicensePlate(licensePlate)) {
            System.out.println("Le numéro d'immatriculation n'est pas valide.");
            System.out.println("Saisir le numéro d'immatriculation : ");
            licensePlate = scanner.nextLine();
        }
        if (!vehiculeService.isLicensePlateInDatabase(licensePlate)) {
            System.out.println("Le numéro d'immatriculation n'est pas enregistré.");
            System.out.println("Voulez-vous l'ajouter ? (O/N)");
            String choiceAdd = scanner.nextLine();
            if (choiceAdd.equalsIgnoreCase("O")) {
                vehiculeService.addLicensePlateToVehicule(licensePlate, client);
            }
        }
        return licensePlate;
    }

    private Reservation addReservationWithLicencePlate(Client client, String LicensePlate, Map.Entry<LocalDateTime, List<Long>> chosenEntry) {
        Long idClient = client.getId();
        Long idVehicule = vehiculeService.getVehiculeIdByLicensePlate(LicensePlate);
        Long idBorne = borneService.getBorneIdOptimal(chosenEntry.getValue(), chosenEntry.getKey());
        LocalDateTime startReservation = chosenEntry.getKey();

        Reservation reservation = new Reservation(idClient, idVehicule, idBorne, startReservation);
        reservationService.addReservation(reservation);
        return reservation;
    }
}
