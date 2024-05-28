package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Vehicule;
import genieLogiciel.projet.borne.repository.VehiculeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VehiculeServiceTests {

    @Mock
    private VehiculeRepository vehiculeRepository;

    @InjectMocks
    private VehiculeService vehiculeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test isLicensePlateInDatabase - plaque d'immatriculation présente")
    void testIsLicensePlateInDatabasePresent() {
        String licensePlate = "AA-123-BB";
        when(vehiculeRepository.findByplaqueImmatriculation(licensePlate)).thenReturn(Optional.of(new Vehicule()));
        assertTrue(vehiculeService.isLicensePlateInDatabase(licensePlate), "La plaque d'immatriculation devrait être présente dans la base de données");
    }

    @Test
    @DisplayName("Test isLicensePlateInDatabase - plaque d'immatriculation absente")
    void testIsLicensePlateInDatabaseAbsent() {
        String licensePlate = "AA-123-BB";
        when(vehiculeRepository.findByplaqueImmatriculation(licensePlate)).thenReturn(Optional.empty());
        assertFalse(vehiculeService.isLicensePlateInDatabase(licensePlate), "La plaque d'immatriculation devrait être absente de la base de données");
    }

    @Test
    @DisplayName("Test getVehiculeIdByLicensePlate - plaque d'immatriculation présente")
    void testGetVehiculeIdByLicensePlatePresent() {
        String licensePlate = "AA-123-BB";
        long vehiculeId = 1L;
        Vehicule vehicule = new Vehicule();
        vehicule.setId(vehiculeId);
        when(vehiculeRepository.findByplaqueImmatriculation(licensePlate)).thenReturn(Optional.of(vehicule));
        assertEquals(vehiculeId, vehiculeService.getVehiculeIdByLicensePlate(licensePlate), "L'ID du véhicule devrait être renvoyé");
    }

    @Test
    @DisplayName("Test getVehiculeIdByLicensePlate - plaque d'immatriculation absente")
    void testGetVehiculeIdByLicensePlateAbsent() {
        String licensePlate = "AA-123-BB";
        when(vehiculeRepository.findByplaqueImmatriculation(licensePlate)).thenReturn(Optional.empty());
        assertNull(vehiculeService.getVehiculeIdByLicensePlate(licensePlate), "L'ID du véhicule devrait être null");
    }

    @Test
    @DisplayName("Test addVehicule")
    void testAddVehicule() {
        Vehicule vehicule = new Vehicule();
        vehiculeService.addVehicule(vehicule);
        verify(vehiculeRepository, times(1)).save(vehicule);
    }
}
