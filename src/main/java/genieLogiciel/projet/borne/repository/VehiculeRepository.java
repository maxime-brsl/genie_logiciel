package genieLogiciel.projet.borne.repository;

import genieLogiciel.projet.borne.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {
    Optional<Vehicule> findByplaqueImmatriculation(String licensePlate);

}
