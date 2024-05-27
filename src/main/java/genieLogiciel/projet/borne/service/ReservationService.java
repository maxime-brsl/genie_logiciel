package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    public Reservation getReservationById(int reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    public List<Reservation> getReservationsByVehiculeId(final Long vehiculeId) {
        return reservationRepository.findByvehiculeId(vehiculeId);
    }

    public Reservation getReservationImminente(final List<Reservation> reservations){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime imminentTime = now.plusMinutes(10);
        Reservation imminentReservation = null;
        for (Reservation reservation : reservations) {
            if (reservation.getHeureDebut().isAfter(now) && reservation.getHeureDebut().isBefore(imminentTime)) {
                imminentReservation = reservation;
            }
        }
        return imminentReservation;
    }

}
