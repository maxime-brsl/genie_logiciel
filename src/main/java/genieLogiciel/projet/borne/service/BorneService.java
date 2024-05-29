package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Borne;
import genieLogiciel.projet.borne.enums.EtatBorne;
import genieLogiciel.projet.borne.repository.BorneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorneService {

    @Autowired
    private BorneRepository borneRepository;

    /**
     * Récupérer toutes les Bornes
     * @return la liste de toutes les Bornes
     */
    public List<Borne> getAllBornes() {
        return borneRepository.findAll();
    }

    /**
     * Changer l'état d'une Borne avec son id
     * @param idBorne l'id de la Borne
     * @param newState le nouvel état de la Borne
     */
    public void changeBorneStateWithId(final Long idBorne, final EtatBorne newState) {
        Borne borneEntity = borneRepository.findById(idBorne).orElse(null);
        if (borneEntity != null) {
            borneEntity.setEtatBorne(newState);
            borneRepository.save(borneEntity);
        }
    }

}