package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testGetReservationById() {
        Reservation reservation = new Reservation();
        reservation.setId(1);

        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        Reservation retrievedReservation = reservationService.getReservationById(1);

        assertEquals(1, retrievedReservation.getId());
    }

    @Test
    public void testGetReservationById_NotFound() {
        when(reservationRepository.findById(2)).thenReturn(Optional.empty());

        Reservation retrievedReservation = reservationService.getReservationById(2);
        assertNull(retrievedReservation);
    }
}
