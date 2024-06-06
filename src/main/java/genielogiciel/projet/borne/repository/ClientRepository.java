package genielogiciel.projet.borne.repository;

import genielogiciel.projet.borne.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Trouver un client par son numéro de téléphone
     *
     * @param phoneNumber le numéro de téléphone du client
     * @return le client
     */
    Optional<Client> findBynumeroTelephone(final String phoneNumber);

}