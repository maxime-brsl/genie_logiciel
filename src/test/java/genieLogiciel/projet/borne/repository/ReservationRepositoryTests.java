package genieLogiciel.projet.borne.repository;

import genieLogiciel.projet.borne.entity.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationRepositoryTests {

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    @DisplayName("Test findById - réservation trouvée")
    void testFindByIdWhenReservationExists() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setVehiculeId(1L);
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        Optional<Reservation> foundReservation = reservationRepository.findById(1);

        assertTrue(foundReservation.isPresent());
        assertEquals(reservation, foundReservation.get());
    }


    @Test
    @DisplayName("Test findByVehiculeId - plusieurs réservations trouvées")
    void testFindByVehiculeIdWhenReservationsExist() {
        Reservation reservation1 = new Reservation();
        reservation1.setId(1L);
        reservation1.setVehiculeId(1L);

        Reservation reservation2 = new Reservation();
        reservation2.setId(2L);
        reservation2.setVehiculeId(1L);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        when(reservationRepository.findByvehiculeId(1L)).thenReturn(reservations);

        List<Reservation> foundReservations = reservationRepository.findByvehiculeId(1L);

        assertEquals(2, foundReservations.size());
        assertTrue(foundReservations.contains(reservation1));
        assertTrue(foundReservations.contains(reservation2));
    }

    @Test
    @DisplayName("Test findByVehiculeId - aucune réservation trouvée")
    void testFindByVehiculeIdWhenNoReservationsExist() {
        when(reservationRepository.findByvehiculeId(1L)).thenReturn(new ArrayList<>());

        List<Reservation> foundReservations = reservationRepository.findByvehiculeId(1L);

        assertEquals(0, foundReservations.size());
    }
}
