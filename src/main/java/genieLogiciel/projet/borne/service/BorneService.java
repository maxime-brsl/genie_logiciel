package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Borne;
import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.enums.EtatBorne;
import genieLogiciel.projet.borne.repository.BorneRepository;
import genieLogiciel.projet.borne.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BorneService {

    @Autowired
    private BorneRepository borneRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Récupérer toutes les Bornes
     *
     * @return la liste de toutes les Bornes
     */
    public List<Borne> getAllBornes() {
        return borneRepository.findAll();
    }

    /**
     * Changer l'état d'une Borne avec son id
     *
     * @param idBorne  l'id de la Borne
     * @param newState le nouvel état de la Borne
     */
    public void changeBorneStateWithId(final Long idBorne, final EtatBorne newState) {
        Borne borneEntity = borneRepository.findById(idBorne).orElse(null);
        if (borneEntity != null) {
            borneEntity.setEtatBorne(newState);
            borneRepository.save(borneEntity);
        }
    }

    /**
     * Récupérer une Borne par son id
     *
     * @param borneId id de la Borne
     * @return
     */
    public Borne getBorneById(final Long borneId) {
        return borneRepository.findById(borneId).orElse(null);
    }

    /**
     * Vérifier si une Borne a une réservation à l'heure actuelle
     *
     * @param borneId id de la Borne
     * @param current date actuelle
     * @return
     */
    public boolean hasReservationAtCurrentTime(final Long borneId, final LocalDateTime current) {
        List<Reservation> reservations = reservationRepository.findByBorneId(borneId);
        for (Reservation reservation : reservations) {
            if (reservation.getHeureDebut().isEqual(current)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculer l'écart entre la réservation en paramètre et celle qui suit
     *
     * @param borneId id de la borne
     * @param start   date de début de la réservation
     * @return
     */
    public int calculateGapNextReservation(final Long borneId, final LocalDateTime start) {
        List<Reservation> reservations = reservationRepository.findByBorneId(borneId);

        List<Reservation> sortedReservations = reservations.stream()
                .sorted(Comparator.comparing(Reservation::getHeureDebut))
                .toList();

        int gap = Integer.MAX_VALUE;
        for (Reservation reservation : sortedReservations) {
            if (reservation.getHeureDebut().isAfter(start)) {
                gap = reservation.getHeureDebut().getHour() - start.getHour();
                break;
            }
        }
        return gap;
    }

    /**
     * Trouver la borne id optimale pour une réservation (celle qui a le plus de temps libre)
     *
     * @param bornes liste des bornes disponibles pour le créneau start
     * @param start  date de début de la réservation
     * @return
     */
    public Long getBorneIdOptimal(final List<Long> bornes, final LocalDateTime start) {
        if (bornes.size() == 1) {
            return bornes.getFirst();
        }
        Long borneIdOptimal = null;
        int gapMax = 0;
        for (Long borneId : bornes) {
            int gap = calculateGapNextReservation(borneId, start);
            if (gap == Integer.MAX_VALUE) {
                return borneId;
            } else if (gap > gapMax) {
                gapMax = gap;
                borneIdOptimal = borneId;
            }
        }
        return borneIdOptimal;
    }

    /**
     * Trouver les créneaux disponibles pour une plage horaire donnée
     *
     * @param start  Début de la plage horaire
     * @param end    Fin de la plage horaire
     * @param bornes Liste des bornes qui seront dispo à cette période
     * @return Map des créneaux disponibles : date + bornes dispo à cette date
     */
    public Map<LocalDateTime, List<Long>> findAvailableDates(final LocalDateTime start, final LocalDateTime end, final List<Long> bornes) {
        System.out.println("Créneaux disponibles pour le " + start.format(DateTimeFormatter.ofPattern("EEEE dd MMM")) + " :");

        if (bornes.isEmpty()) {
            System.out.println("Aucune borne disponible pour cette plage horaire.");
            return new HashMap<>();
        }
        
        Map<LocalDateTime, List<Long>> creneaux = new HashMap<>();

        for (LocalDateTime current = start; !current.isAfter(end); current = current.plusHours(1)) {
            List<Long> bornesAvailable = new ArrayList<>();

            for (Long borne : bornes) {
                if (!hasReservationAtCurrentTime(borne, current)) {
                    bornesAvailable.add(borne);
                }
            }
            creneaux.put(current, bornesAvailable);
        }
        return creneaux;
    }
}