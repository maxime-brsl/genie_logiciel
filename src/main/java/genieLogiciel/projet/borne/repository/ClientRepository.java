package genieLogiciel.projet.borne.repository;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findBynumeroTel(String phoneNumber);

}