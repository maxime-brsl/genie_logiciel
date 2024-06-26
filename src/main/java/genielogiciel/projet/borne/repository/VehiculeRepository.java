package genielogiciel.projet.borne.repository;

import genielogiciel.projet.borne.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {

    /**
     * Trouver un véhicule par plaque d'immatriculation
     *
     * @param licensePlate plaque d'immatriculation
     * @return le véhicule
     */
    Optional<Vehicule> findByplaqueImmatriculation(final String licensePlate);

}
