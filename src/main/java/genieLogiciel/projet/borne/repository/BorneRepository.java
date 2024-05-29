package genieLogiciel.projet.borne.repository;

import genieLogiciel.projet.borne.entity.Borne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorneRepository extends JpaRepository<Borne, Long> {

}