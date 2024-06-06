package genielogiciel.projet.borne.repository;

import genielogiciel.projet.borne.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByvehiculeId(final Long vehiculeId);

    List<Reservation> findByBorneId(final Long borneId);

    List<Reservation> findByClientId(final Long clientId);
}