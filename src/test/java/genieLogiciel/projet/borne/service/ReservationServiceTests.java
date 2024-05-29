package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.enums.EtatReservation;
import genieLogiciel.projet.borne.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    private LocalDateTime now;

    @BeforeEach
    public void setUp() {
        now = LocalDateTime.now();
    }

    @Test
    @DisplayName("Test getReservationById - réservation trouvée")
    public void testGetReservationById() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        Reservation retrievedReservation = reservationService.getReservationById(1);

        assertEquals(1, retrievedReservation.getId());
    }

    @Test
    @DisplayName("Test getReservationById - réservation non trouvée")
    public void testGetReservationById_NotFound() {
        when(reservationRepository.findById(2)).thenReturn(Optional.empty());

        Reservation retrievedReservation = reservationService.getReservationById(2);
        assertNull(retrievedReservation);
    }

    @Test
    @DisplayName("Aucune réservation")
    public void testGetReservationImminente_NoReservation() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation imminentReservation = reservationService.getReservationImminente(reservations);
        assertNull(imminentReservation);
    }

    @Test
    @DisplayName("Une seule réservation, pas imminente")
    public void testGetReservationImminente_SingleReservationNotImminent() {
        Reservation reservation = new Reservation();
        reservation.setHeureDebut(now.plusMinutes(20));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        Reservation imminentReservation = reservationService.getReservationImminente(reservations);
        assertNull(imminentReservation);
    }

    @Test
    @DisplayName("Une seule réservation, imminente")
    public void testGetReservationImminente_SingleReservationImminent() {
        Reservation reservation = new Reservation();
        reservation.setHeureDebut(now.plusMinutes(5));
        reservation.setEtatReservation(EtatReservation.EN_ATTENTE);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        Reservation imminentReservation = reservationService.getReservationImminente(reservations);
        assertNotNull(imminentReservation);
        assertEquals(reservation, imminentReservation);
    }

    @Test
    @DisplayName("Plusieurs réservations imminentes")
    public void testGetReservationImminente_MultipleReservationsImminent() {
        Reservation imminentReservation = new Reservation();
        imminentReservation.setHeureDebut(now.plusMinutes(5));
        imminentReservation.setEtatReservation(EtatReservation.EN_ATTENTE);

        Reservation nonImminentReservation = new Reservation();
        nonImminentReservation.setHeureDebut(now.plusMinutes(15));
        nonImminentReservation.setEtatReservation(EtatReservation.EN_ATTENTE);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(imminentReservation);
        reservations.add(nonImminentReservation);

        Reservation result = reservationService.getReservationImminente(reservations);
        assertNotNull(result);
        assertEquals(imminentReservation, result);
    }

    @Test
    @DisplayName("Plusieurs réservations, aucune imminente")
    public void testGetReservationImminente_MultipleReservationsNotImminent() {
        Reservation reservation1 = new Reservation();
        reservation1.setHeureDebut(now.plusMinutes(15));

        Reservation reservation2 = new Reservation();
        reservation2.setHeureDebut(now.plusMinutes(20));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        Reservation result = reservationService.getReservationImminente(reservations);
        assertNull(result);
    }

    @Test
    @DisplayName("Test getReservationsByVehiculeId - vehicule id présent")
    public void testGetReservationsByVehiculeIdPresent() {
        long vehiculeId = 1L;
        List<Reservation> reservations = new ArrayList<>();
        when(reservationRepository.findByvehiculeId(vehiculeId)).thenReturn(reservations);
        assertEquals(reservations, reservationService.getReservationsByVehiculeId(vehiculeId), "Les réservations devraient être renvoyées");
    }

    @Test
    @DisplayName("Test getReservationsByVehiculeId - vehicule id absent")
    public void testGetReservationsByVehiculeIdAbsent() {
        long vehiculeId = 1L;
        when(reservationRepository.findByvehiculeId(vehiculeId)).thenReturn(new ArrayList<>());
        assertTrue(reservationService.getReservationsByVehiculeId(vehiculeId).isEmpty(), "Aucune réservation ne devrait être renvoyée");
    }

    @Test
    @DisplayName("Test changeReservationState")
    public void testChangeReservationState() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setEtatReservation(EtatReservation.EN_ATTENTE);

        EtatReservation newState = EtatReservation.EN_COURS;

        reservationService.changeReservationState(reservation, newState);

        assertEquals(newState, reservation.getEtatReservation());
    }

    @Test
    @DisplayName("Test isReservationImminente - réservation imminente")
    public void testIsReservationImminente() {
        Reservation reservation = new Reservation();
        reservation.setHeureDebut(now.plusMinutes(5));
        reservation.setEtatReservation(EtatReservation.EN_ATTENTE);

        assertTrue(reservationService.isReservationImminente(reservation));
    }

    @Test
    @DisplayName("Test isReservationImminente - réservation non imminente")
    public void testIsReservationImminente_NotImminent() {
        Reservation reservation = new Reservation();
        reservation.setHeureDebut(now.plusMinutes(15));
        reservation.setEtatReservation(EtatReservation.EN_ATTENTE);

        assertFalse(reservationService.isReservationImminente(reservation));
    }
}
