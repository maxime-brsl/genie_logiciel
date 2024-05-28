package genieLogiciel.projet.borne.repository;

import genieLogiciel.projet.borne.entity.Vehicule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehiculeRepositoryTests {

    @Mock
    private VehiculeRepository vehiculeRepository;

    @Test
    @DisplayName("Test findByPlaqueImmatriculation - véhicule trouvé")
    void testFindByPlaqueImmatriculationWhenVehiculeExists() {
        Vehicule vehicule = new Vehicule();
        vehicule.setId(1L);
        vehicule.setPlaqueImmatriculation("ABC123");
        when(vehiculeRepository.findByplaqueImmatriculation("ABC123")).thenReturn(Optional.of(vehicule));

        Optional<Vehicule> foundVehicule = vehiculeRepository.findByplaqueImmatriculation("ABC123");

        assertTrue(foundVehicule.isPresent());
        assertEquals(vehicule, foundVehicule.get());
    }

    @Test
    @DisplayName("Test findByPlaqueImmatriculation - aucun véhicule trouvé")
    void testFindByPlaqueImmatriculationWhenNoVehiculeExists() {
        when(vehiculeRepository.findByplaqueImmatriculation("DEF456")).thenReturn(Optional.empty());

        Optional<Vehicule> foundVehicule = vehiculeRepository.findByplaqueImmatriculation("DEF456");

        assertTrue(foundVehicule.isEmpty());
    }
}
