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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    public void testGetReservationImminente_NoReservation() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation imminentReservation = reservationService.getReservationImminente(reservations);
        assertNull(imminentReservation);
    }

    @Test
    public void testGetReservationImminente_SingleReservationNotImminent() {
        LocalDateTime now = LocalDateTime.now();
        Reservation reservation = new Reservation();
        reservation.setHeureDebut(now.plusMinutes(20));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        Reservation imminentReservation = reservationService.getReservationImminente(reservations);
        assertNull(imminentReservation);
    }

    @Test
    public void testGetReservationImminente_SingleReservationImminent() {
        LocalDateTime now = LocalDateTime.now();
        Reservation reservation = new Reservation();
        reservation.setHeureDebut(now.plusMinutes(5));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        Reservation imminentReservation = reservationService.getReservationImminente(reservations);
        assertNotNull(imminentReservation);
        assertEquals(reservation, imminentReservation);
    }

    @Test
    public void testGetReservationImminente_MultipleReservationsImminent() {
        LocalDateTime now = LocalDateTime.now();

        Reservation imminentReservation = new Reservation();
        imminentReservation.setHeureDebut(now.plusMinutes(5));

        Reservation nonImminentReservation = new Reservation();
        nonImminentReservation.setHeureDebut(now.plusMinutes(15));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(imminentReservation);
        reservations.add(nonImminentReservation);

        Reservation result = reservationService.getReservationImminente(reservations);
        assertNotNull(result);
        assertEquals(imminentReservation, result);
    }
}
