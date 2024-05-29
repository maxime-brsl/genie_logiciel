package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Borne;
import genieLogiciel.projet.borne.entity.Reservation;
import genieLogiciel.projet.borne.enums.EtatBorne;
import genieLogiciel.projet.borne.enums.EtatReservation;
import genieLogiciel.projet.borne.repository.BorneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorneService {

    @Autowired
    private BorneRepository borneRepository;

    public List<Borne> getAllBornes() {
        return borneRepository.findAll();
    }

    public void changeBorneStateWithId(long borne, EtatBorne newState) {
        Borne borneEntity = borneRepository.findById(borne).orElse(null);
        if (borneEntity != null) {
            borneEntity.setEtatBorne(newState);
            borneRepository.save(borneEntity);
        }
    }

}