package genielogiciel.projet.borne.service;

import genielogiciel.projet.borne.entity.Reservation;
import genielogiciel.projet.borne.enums.EtatReservation;
import genielogiciel.projet.borne.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation getReservationById(final int reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    public List<Reservation> getReservationsByVehiculeId(final Long vehiculeId) {
        return reservationRepository.findByvehiculeId(vehiculeId);
    }

    public List<Reservation> getReservationsByBorneId(final Long borneId) {
        return reservationRepository.findByBorneId(borneId);
    }

    public Reservation getReservationImminente(final List<Reservation> reservations) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime imminentTime = now.plusMinutes(10);
        Reservation imminentReservation = null;
        for (Reservation reservation : reservations) {
            if (reservation.getHeureDebut().isAfter(now) && reservation.getHeureDebut().isBefore(imminentTime) && reservation.getEtatReservation().equals(EtatReservation.EN_ATTENTE)) {
                imminentReservation = reservation;
            }
        }
        return imminentReservation;
    }

    /**
     * Vérifier si une réservation est imminente
     *
     * @param reservations la liste des réservations
     * @return true si une réservation est imminente, false sinon
     */
    public boolean isReservationImminente(final Reservation reservations) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime imminentTime = now.plusMinutes(10);
        return reservations.getHeureDebut().isAfter(now) && reservations.getHeureDebut().isBefore(imminentTime) && reservations.getEtatReservation().equals(EtatReservation.EN_ATTENTE);
    }

    /**
     * Changer l'état d'une réservation
     *
     * @param reservation la réservation à modifier
     * @param newState    le nouvel état de la réservation
     */
    public void changeReservationState(final Reservation reservation, final EtatReservation newState) {
        reservation.setEtatReservation(newState);
        reservationRepository.save(reservation);
    }

    /**
     * Vérifier si la réservation est dans la période d'attente
     *
     * @param reservation la réservation à vérifier
     * @return true si la réservation est dans la période d'attente, false sinon
     */
    public boolean isPeriodAttente(final Reservation reservation) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime attenteTime = reservation.getHeureDebut().plusMinutes(10);
        return now.isEqual(reservation.getHeureDebut()) || (now.isAfter(reservation.getHeureDebut()) && now.isBefore(attenteTime));
    }

    public void addReservation(final Reservation reservation) {
        reservationRepository.save(reservation);
    }

}
