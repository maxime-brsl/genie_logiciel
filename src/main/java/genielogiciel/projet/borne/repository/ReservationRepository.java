package genielogiciel.projet.borne.repository;

import genielogiciel.projet.borne.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    /**
     * Trouver une réservation par l'id du véhicule
     *
     * @param vehiculeId l'id du véhicule
     * @return la réservation
     */
    List<Reservation> findByvehiculeId(final Long vehiculeId);

    /**
     * Trouver une réservation par l'id de la borne
     *
     * @param borneId l'id de la borne
     * @return la réservation
     */
    List<Reservation> findByBorneId(final Long borneId);

    List<Reservation> findByClientId(final Long clientId);
}
