package genieLogiciel.projet.borne.menu;

import genieLogiciel.projet.borne.entity.Borne;
import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.enums.EtatReservation;
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
        List<Borne> bornes = borneService.getAllBornes();
        boolean running = true;
        while (running) {
            displayOptions();
            System.out.println("Choisissez une option : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    LocalDateTime chosenDateStart = chooseTimeSlot();
                    Map<LocalDateTime, List<Long>> creneaux = findAvailableReservations(chosenDateStart, bornes);
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
     *
     * @return
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
     * Trouver les créneaux disponibles pour une date donnée
     *
     * @param start  Date de début
     * @param bornes Liste des bornes qui seront dispo à cette date
     * @return
     */
    public Map<LocalDateTime, List<Long>> findAvailableReservations(LocalDateTime start, List<Borne> bornes) {
        //Création de la plage horaire pour afficher les créneaux disponibles
        LocalDateTime end = start.plusHours(12);
        System.out.println("Créneaux disponibles pour le " + start.format(DateTimeFormatter.ofPattern("EEEE dd MMM")) + " :");

        Map<LocalDateTime, List<Long>> creneaux = new HashMap<>();

        // Parcours des heures entre dateDebut et dateFin
        for (LocalDateTime current = start; !current.isAfter(end); current = current.plusHours(1)) {
            List<Long> bornesAvailable = new ArrayList<>();

            // Vérification de la disponibilité des bornes pour l'heure actuelle
            for (Borne borne : bornes) {
                if (!borneService.hasReservationAtCurrentTime(borne.getId(), current)) {
                    bornesAvailable.add(borne.getId());
                }
            }
            creneaux.put(current, bornesAvailable);
        }
        return creneaux;
    }

    /**
     * Choisir une réservation parmi les créneaux disponibles
     *
     * @param creneaux Map des créneaux disponibles : date + bornes dispo à cette date
     * @param client   Client qui fait la réservation
     */
    public void chooseReservation(Map<LocalDateTime, List<Long>> creneaux, Client client) {
        if (creneaux.isEmpty()) {
            System.out.println("Aucune borne disponible pour ce créneau.");
            return;
        }
        Map<LocalDateTime, List<Long>> sortedCreneaux = new TreeMap<>(creneaux);
        List<Map.Entry<LocalDateTime, List<Long>>> creneauxList = new ArrayList<>(sortedCreneaux.entrySet());
        for (int i = 0; i < creneauxList.size(); i++) {
            Map.Entry<LocalDateTime, List<Long>> entry = creneauxList.get(i);
            System.out.println(i + ".");
            System.out.println("Créneau : " + entry.getKey().format(DateTimeFormatter.ofPattern("EEEE dd MMM HH:mm")));
            System.out.println("Bornes disponibles : " + entry.getValue());
        }

        System.out.println("Entrez le nombre du créneau que vous souhaitez réserver : ");
        System.out.println("Entrez un autre chiffre pour annuler.");
        int choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            if (!(choice >= 0 && choice < creneaux.size())) {
                return;
            }
        } else {
            scanner.next();
            return;
        }

        Map.Entry<LocalDateTime, List<Long>> chosenEntry = creneauxList.get(choice);

        System.out.println("Saisir le numéro d'immatriculation pour la réservation : ");
        scanner.nextLine(); // Clear buffer
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
        Long idClient = client.getId();
        Long idVehicule = vehiculeService.getVehiculeIdByLicensePlate(licensePlate);
        Long idBorne = borneService.getBorneIdOptimal(chosenEntry.getValue(), chosenEntry.getKey());
        LocalDateTime startReservation = chosenEntry.getKey();

        Reservation reservation = createReservation(idClient, idVehicule, idBorne, startReservation);
        reservationService.addReservation(reservation);
        System.out.println("Créneau réservé avec succès !");
        System.out.println(reservation);


        //Gérer un créneau de + d'une heure
        System.out.println("Voulez-vous réserver une heure supplémentaire ? (O/N)");

        while (scanner.nextLine().equalsIgnoreCase("O")) {
            System.out.println("Nous regardons si la borne est disponible pour le créneau suivant.");
            if (borneService.hasReservationAtCurrentTime(idBorne, startReservation.plusHours(1))) {
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

    /**
     * Créer une réservation
     *
     * @param clientId   Id du client
     * @param vehiculeId Id du véhicule
     * @param borneId    Id de la borne
     * @param start      Date de début de la réservation
     */
    private Reservation createReservation(Long clientId, Long vehiculeId, Long borneId, LocalDateTime start) {
        Reservation reservation = new Reservation();
        reservation.setClientId(clientId);
        reservation.setVehiculeId(vehiculeId);
        reservation.setBorneId(borneId);
        reservation.setHeureDebut(start);
        reservation.setHeureFinP(start.plusHours(1));
        reservation.setEtatReservation(EtatReservation.EN_ATTENTE);

        return reservation;
    }


}
