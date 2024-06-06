package genielogiciel.projet.borne.service;

import genielogiciel.projet.borne.entity.Borne;
import genielogiciel.projet.borne.entity.Reservation;
import genielogiciel.projet.borne.enums.EtatBorne;
import genielogiciel.projet.borne.repository.BorneRepository;
import genielogiciel.projet.borne.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorneServiceTests {

    @Mock
    private BorneRepository borneRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private BorneService borneService;

    @InjectMocks
    private ReservationService reservationService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getAllBornes - plusieurs bornes")
    void getAllBornes() {
        Borne borne1 = new Borne();
        Borne borne2 = new Borne();
        List<Borne> expectedBornes = Arrays.asList(borne1, borne2);
        when(borneRepository.findAll()).thenReturn(expectedBornes);

        List<Borne> actualBornes = borneService.getAllBornes();

        assertEquals(expectedBornes, actualBornes);
        verify(borneRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getAllBornes - aucune borne")
    void getAllBornesAucuneBorne() {
        when(borneRepository.findAll()).thenReturn(new ArrayList<>());

        List<Borne> actualBornes = borneService.getAllBornes();

        assertTrue(actualBornes.isEmpty());
        verify(borneRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test changeBorneStateWithId - borne existante")
    void changeBorneStateWithId() {
        long borneId = 1L;
        EtatBorne newState = EtatBorne.DISPONIBLE;
        Borne borne = new Borne();
        borne.setId(borneId);
        when(borneRepository.findById(borneId)).thenReturn(Optional.of(borne));

        borneService.changeBorneStateWithId(borneId, newState);

        assertEquals(newState, borne.getEtatBorne());
        verify(borneRepository, times(1)).findById(borneId);
        verify(borneRepository, times(1)).save(borne);
    }

    @Test
    @DisplayName("Test changeBorneStateWithId - borne inexistante")
    void changeBorneStateWithIdBorneInexistante() {
        long borneId = 1L;
        EtatBorne newState = EtatBorne.DISPONIBLE;
        when(borneRepository.findById(borneId)).thenReturn(Optional.empty());

        borneService.changeBorneStateWithId(borneId, newState);

        verify(borneRepository, times(1)).findById(borneId);
        verify(borneRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test hasReservationAtCurrentTime - réservation à l'heure actuelle")
    void testHasReservationAtCurrentTime() {
        Long borneId = 1L;
        LocalDateTime current = LocalDateTime.now();
        Reservation reservation = new Reservation();
        reservation.setHeureDebut(current);
        when(reservationRepository.findByBorneId(borneId)).thenReturn(Arrays.asList(reservation));

        assertTrue(borneService.hasReservationAtCurrentTime(borneId, current));
        verify(reservationRepository, times(1)).findByBorneId(borneId);
    }

    @Test
    @DisplayName("Test hasReservationAtCurrentTime - aucune réservation à l'heure actuelle")
    void testHasNoReservationAtCurrentTime() {
        Long borneId = 1L;
        LocalDateTime current = LocalDateTime.now();
        when(reservationRepository.findByBorneId(borneId)).thenReturn(new ArrayList<>());

        assertFalse(borneService.hasReservationAtCurrentTime(borneId, current));
        verify(reservationRepository, times(1)).findByBorneId(borneId);
    }

    @Test
    @DisplayName("Test calculateGapNextReservation - aucune réservation trouvée")
    void testCalculateGapNextReservationNoReservationFound() {
        long borneId = 1L;
        LocalDateTime start = LocalDateTime.now();
        when(reservationRepository.findByBorneId(borneId)).thenReturn(new ArrayList<>());

        int gap = borneService.calculateGapNextReservation(borneId, start);

        assertEquals(Integer.MAX_VALUE, gap);
    }


    @Test
    @DisplayName("Test calculateGapNextReservation - plusieurs bornes avec réservations")
    void testCalculateGapNextReservationMultipleBornesWithReservations() {
        long borneId1 = 1L;
        long borneId2 = 2L;
        LocalDateTime start = LocalDateTime.of(2024, 5, 30, 10, 0); // 30 mai 2024, 10h00
        LocalDateTime reservationStart1 = LocalDateTime.of(2024, 5, 30, 11, 0); // 30 mai 2024, 11h00
        LocalDateTime reservationStart2 = LocalDateTime.of(2024, 5, 30, 12, 0); // 30 mai 2024, 12h00
        Reservation reservation1 = new Reservation();
        reservation1.setHeureDebut(reservationStart1);
        Reservation reservation2 = new Reservation();
        reservation2.setHeureDebut(reservationStart2);
        when(reservationRepository.findByBorneId(borneId1)).thenReturn(Arrays.asList(reservation1));
        when(reservationRepository.findByBorneId(borneId2)).thenReturn(Arrays.asList(reservation2));

        int gapBorne1 = borneService.calculateGapNextReservation(borneId1, start);
        int gapBorne2 = borneService.calculateGapNextReservation(borneId2, start);

        assertEquals(1, gapBorne1);
        assertEquals(2, gapBorne2);
    }

    @Test
    @DisplayName("Test calculateGapNextReservation - plusieurs bornes avec réservations et une sans réservation")
    void testCalculateGapNextReservationMultipleBornesWithAndWithoutReservations() {
        long borneId1 = 1L;
        long borneId2 = 2L;
        LocalDateTime start = LocalDateTime.of(2024, 5, 30, 10, 0); // 30 mai 2024, 10h00
        LocalDateTime reservationStart1 = LocalDateTime.of(2024, 5, 30, 11, 0); // 30 mai 2024, 11h00
        LocalDateTime reservationStart2 = LocalDateTime.of(2024, 5, 30, 12, 0); // 30 mai 2024, 12h00
        Reservation reservation1 = new Reservation();
        reservation1.setHeureDebut(reservationStart1);
        Reservation reservation2 = new Reservation();
        reservation2.setHeureDebut(reservationStart2);
        when(reservationRepository.findByBorneId(borneId1)).thenReturn(Arrays.asList(reservation1));
        when(reservationRepository.findByBorneId(borneId2)).thenReturn(new ArrayList<>()); // Aucune réservation pour cette borne

        int gapBorne1 = borneService.calculateGapNextReservation(borneId1, start);
        int gapBorne2 = borneService.calculateGapNextReservation(borneId2, start);

        assertEquals(1, gapBorne1); // Devrait être 1 heure de différence
        assertEquals(Integer.MAX_VALUE, gapBorne2); // Aucune réservation trouvée, donc devrait être Integer.MAX_VALUE
    }

    @Test
    @DisplayName("Test getBorneIdOptimal - une seule borne")
    void testGetBorneIdOptimalSingleBorne() {
        long borneId = 1L;
        List<Long> bornes = List.of(borneId);
        LocalDateTime start = LocalDateTime.now();

        Long optimalBorneId = borneService.getBorneIdOptimal(bornes, start);

        assertEquals(borneId, optimalBorneId);
    }

    @Test
    @DisplayName("Test getBorneIdOptimal - plusieurs bornes")
    void testGetBorneIdOptimalMultipleBornes() {
        long borneId1 = 1L;
        long borneId2 = 2L;
        List<Long> bornes = List.of(borneId1, borneId2);
        LocalDateTime start = LocalDateTime.now();
        when(reservationRepository.findByBorneId(borneId1)).thenReturn(new ArrayList<>());
        when(reservationRepository.findByBorneId(borneId2)).thenReturn(new ArrayList<>());

        Long optimalBorneId = borneService.getBorneIdOptimal(bornes, start);

        assertEquals(borneId1, optimalBorneId);
    }

    @Test
    @DisplayName("Test getBorneIdOptimal - plusieurs bornes avec et sans réservations")
    void testGetBorneIdOptimalMultipleBornesWithAndWithoutReservations() {
        long borneId1 = 1L;
        long borneId2 = 2L;
        List<Long> bornes = List.of(borneId1, borneId2);
        LocalDateTime start = LocalDateTime.of(2024, 5, 30, 10, 0); // 30 mai 2024, 10h00
        LocalDateTime reservationStart = LocalDateTime.of(2024, 5, 30, 11, 0); // 30 mai 2024, 11h00
        Reservation reservation = new Reservation();
        reservation.setHeureDebut(reservationStart);
        when(reservationRepository.findByBorneId(borneId1)).thenReturn(List.of(reservation));
        when(reservationRepository.findByBorneId(borneId2)).thenReturn(new ArrayList<>());

        Long optimalBorneId = borneService.getBorneIdOptimal(bornes, start);

        assertEquals(borneId2, optimalBorneId);
    }

    @Test
    @DisplayName("La fonction renvoie une map vide si la liste de bornes est vide")
    void testFindAvailableDatesWithEmptyBornes() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusHours(12);
        List<Long> bornes = new ArrayList<>();
        Map<LocalDateTime, List<Long>> result = borneService.findAvailableDates(start, end, bornes);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("La fonction renvoie les bons créneaux disponibles pour une date et une liste de bornes données")
    void testFindAvailableDates() {
        LocalDateTime start = LocalDateTime.of(2022, 12, 15, 8, 0);
        LocalDateTime end = LocalDateTime.of(2022, 12, 15, 12, 0);
        long borneId1 = 1L;
        long borneId2 = 2L;
        List<Long> bornes = List.of(borneId1, borneId2);
        Map<LocalDateTime, List<Long>> expected = new HashMap<>();
        expected.put(LocalDateTime.of(2022, 12, 15, 8, 0), Arrays.asList(1L, 2L));
        expected.put(LocalDateTime.of(2022, 12, 15, 9, 0), Arrays.asList(1L, 2L));
        expected.put(LocalDateTime.of(2022, 12, 15, 10, 0), Arrays.asList(1L, 2L));
        expected.put(LocalDateTime.of(2022, 12, 15, 11, 0), Arrays.asList(1L, 2L));
        expected.put(LocalDateTime.of(2022, 12, 15, 12, 0), Arrays.asList(1L, 2L));
        Map<LocalDateTime, List<Long>> result = borneService.findAvailableDates(start, end, bornes);
        assertEquals(expected, result);
    }
}
