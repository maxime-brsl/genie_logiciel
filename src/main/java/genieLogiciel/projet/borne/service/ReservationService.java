package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    public Reservation getReservationById(int reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

}
