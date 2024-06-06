package genielogiciel.projet.borne.menu;

import genielogiciel.projet.borne.entity.Borne;
import genielogiciel.projet.borne.entity.Client;
import genielogiciel.projet.borne.entity.Reservation;
import genielogiciel.projet.borne.service.BorneService;
import genielogiciel.projet.borne.service.ReservationService;
import genielogiciel.projet.borne.service.VehiculeService;
import genielogiciel.projet.borne.util.LicensePlateValidator;
import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@Component
public class CreateReservationMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(CreateReservationMenu.class));
    private static final String DATE_FORMAT = "EEEE dd MMM";
    private static final String DATE_FORMAT_WITH_HOURS = DATE_FORMAT + " HH:mm";

    private static BorneService borneService;
    private static VehiculeService vehiculeService;
    private static ReservationService reservationService;

    @Autowired
    private CreateReservationMenu(BorneService borneService, VehiculeService vehiculeService, ReservationService reservationService) {
        CreateReservationMenu.borneService = borneService;
        CreateReservationMenu.vehiculeService = vehiculeService;
        CreateReservationMenu.reservationService = reservationService;
    }

    /**
     * Afficher le menu de création de réservation
     *
     * @param client Client qui fait la réservation
     */
    public static void displayCreateReservationMenu(final Client client) {
        List<Borne> listBornes = borneService.getAllBornes();
        boolean running = true;
        while (running) {
            displayOptions();
            LOG.info(TextMenu.CHOISIR_UNE_OPTION);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    LocalDateTime chosenDateStart = chooseTimeSlot();
                    Map<LocalDateTime, List<Long>> crenauxDisponible = borneService.findAvailableDates(chosenDateStart, chosenDateStart.plusHours(12), listBornes);

                    chooseReservation(crenauxDisponible, client);
                    break;
                case "2":
                    MainMenu.displayMainMenu();
                    running = false;
                    break;
                default:
                    LOG.info(TextMenu.OPTION_INVALIDE);
            }
        }
        scanner.close();
    }

    /**
     * Afficher les options du menu de réservation
     */
    private static void displayOptions() {
        String menu = """
                
                 ----- Réservation -----
                 1. Faire une réservation
                 2. %s
                \s""".formatted(TextMenu.RETOUR_MENU_PRINCIPAL);
        LOG.info(menu);
    }

    /**
     * Choisir un créneau horaire pour la réservation
     * on affiche les jours à la semaine et on divise en deux par plage horaire pour limiter l'affichage
     */
    public static LocalDateTime chooseTimeSlot() {
        LocalDateTime today = LocalDateTime.now();

        // Affiche les 7 jours à venir à partir d'aujourd'hui
        choisirDateReservation(today);
        LOG.info("7. Semaine suivante");
        LOG.info("Entrez un nombre (0-7): ");
        int day = scanner.nextInt();
        while (day == 7) {
            today = today.plusDays(7);
            LOG.info("Semaine suivante :");
            choisirDateReservation(today);
            day = day + scanner.nextInt();
        }

        LOG.info("Voulez-vous réserver pour le matin (de 00h à 12h) ou l'après-midi (de 12h à 00h) ? (1/2)");
        long period = scanner.nextLong();
        period = period - 1;

        LocalDateTime chosenDate = today.plusDays(day).with(LocalTime.MIDNIGHT);
        chosenDate = chosenDate.plusHours(12 * period);
        return chosenDate;
    }

    /**
     * Afficher les jours de la semaine pour la réservation
     *
     * @param today date actuelle
     */
    private static void choisirDateReservation(LocalDateTime today) {
        LOG.info("Veuillez choisir quel jour vous souhaitez faire une réservation :");
        for (int i = 0; i <= 6; i++) {
            LocalDateTime futureDate = today.plusDays(i);
            LOG.info(i + ". " + futureDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        }
    }

    /**
     * Choisir une réservation parmi les créneaux disponibles
     *
     * @param creneaux Map des créneaux disponibles : date + bornes dispo à cette date
     * @param client   Client qui fait la réservation
     */
    public static void chooseReservation(final Map<LocalDateTime, List<Long>> creneaux, final Client client) {
        List<Integer> impossible = new ArrayList<>();
        if (creneaux.isEmpty()) {
            LOG.info("Aucune borne disponible pour cette plage horaire.");
            return;
        }
        Map<LocalDateTime, List<Long>> sortedCreneaux = new TreeMap<>(creneaux);
        List<Map.Entry<LocalDateTime, List<Long>>> creneauxList = new ArrayList<>(sortedCreneaux.entrySet());
        for (int i = 0; i < creneauxList.size(); i++) {
            Map.Entry<LocalDateTime, List<Long>> entry = creneauxList.get(i);
            LOG.info(i + ".");
            LOG.info("Créneau : " + entry.getKey().format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_HOURS)));
            if (entry.getValue().isEmpty()) {
                LOG.info("Aucune borne disponible.");
                impossible.add(i);
            } else {
                LOG.info("Bornes disponibles : " + entry.getValue());
            }
        }

        LOG.info("Entrez le nombre du créneau que vous souhaitez réserver : ");
        int choice;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            if (!(choice >= 0 && choice < creneauxList.size())) {
                return;
            }
            if (impossible.contains(choice)) {
                LOG.info("Créneau impossible à réserver.");
                return;
            }
        } else {
            scanner.next();
            return;
        }
        Map.Entry<LocalDateTime, List<Long>> chosenEntry = creneauxList.get(choice);
        String licensePlate = inputLicensePlate(client);
        Reservation reservation = addReservationWithLicensePlate(client, licensePlate, chosenEntry);
        reservationService.addReservation(reservation);
        LOG.info("Créneau réservé avec succès !");
        LOG.info(reservation.toString());
        reserveAdditionalHour(reservation);
    }

    private static void reserveAdditionalHour(final Reservation reservation) {
        LOG.info("Voulez-vous réserver une heure supplémentaire ? (O/N)");
        while (scanner.nextLine().equalsIgnoreCase("O")) {
            LOG.info("Nous regardons si la borne est disponible pour le créneau suivant.");
            if (borneService.hasReservationAtCurrentTime(reservation.getBorneId(), reservation.getHeureFinP().plusHours(1))) {
                LOG.info("Aucune borne disponible pour le créneau suivant.");
                return;
            } else {
                reservation.setHeureFinP(reservation.getHeureFinP().plusHours(1));
                LOG.info("Créneau réservé avec succès !");
                LOG.info(reservation.toString());
            }
            LOG.info("Voulez-vous réserver une heure supplémentaire ? (O/N)");
        }
    }

    private static String inputLicensePlate(final Client client) {
        LOG.info("Saisir le numéro d'immatriculation pour la réservation : ");
        scanner.nextLine();
        String licensePlate = scanner.nextLine();

        while (!LicensePlateValidator.isValidLicensePlate(licensePlate)) {
            LOG.info(TextMenu.IMMATRICULATION_INVALIDE);
            LOG.info(TextMenu.SAISIR_NUMERO_IMMATRICULATION);
            licensePlate = scanner.nextLine();
        }
        if (!vehiculeService.isLicenseplateExist(licensePlate)) {
            LOG.info("Le numéro d'immatriculation n'est pas enregistré.");
            LOG.info("Voulez-vous l'ajouter ? (O/N)");
            String choiceAdd = scanner.nextLine();
            if (choiceAdd.equalsIgnoreCase("O")) {
                vehiculeService.addVehiculeToClient(licensePlate, client);
            }
        }
        return licensePlate;
    }

    private static Reservation addReservationWithLicensePlate(final Client client, final String LicensePlate, final Map.Entry<LocalDateTime, List<Long>> chosenEntry) {
        Long idClient = client.getId();
        Long idVehicule = vehiculeService.getVehiculeIdByLicensePlate(LicensePlate);
        Long idBorne = borneService.getBorneIdOptimal(chosenEntry.getValue(), chosenEntry.getKey());
        LocalDateTime startReservation = chosenEntry.getKey();

        Reservation reservation = new Reservation(idClient, idVehicule, idBorne, startReservation);
        reservationService.addReservation(reservation);
        return reservation;
    }
}
