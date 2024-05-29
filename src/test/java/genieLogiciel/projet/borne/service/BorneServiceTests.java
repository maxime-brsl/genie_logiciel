package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Borne;
import genieLogiciel.projet.borne.enums.EtatBorne;
import genieLogiciel.projet.borne.repository.BorneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BorneServiceTests {

    @Mock
    private BorneRepository borneRepository;

    @InjectMocks
    private BorneService borneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getAllBornes - plusieurs bornes")
    void getAllBornes() {
        Borne borne1 = new Borne();
        Borne borne2 = new Borne();
        List<Borne> expectedBornes = Arrays.asList(borne1, borne2);
        when(borneRepository.findAll()).thenReturn(expectedBornes);

        List<Borne> actualBornes = borneService.getAllBornes();

        assertEquals(expectedBornes, actualBornes);
        verify(borneRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getAllBornes - aucune borne")
    void getAllBornesAucuneBorne() {
        when(borneRepository.findAll()).thenReturn(new ArrayList<>());

        List<Borne> actualBornes = borneService.getAllBornes();

        assertTrue(actualBornes.isEmpty());
        verify(borneRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test changeBorneStateWithId - borne existante")
    void changeBorneStateWithId() {
        long borneId = 1L;
        EtatBorne newState = EtatBorne.DISPONIBLE;
        Borne borne = new Borne();
        borne.setId(borneId);
        when(borneRepository.findById(borneId)).thenReturn(Optional.of(borne));

        borneService.changeBorneStateWithId(borneId, newState);

        assertEquals(newState, borne.getEtatBorne());
        verify(borneRepository, times(1)).findById(borneId);
        verify(borneRepository, times(1)).save(borne);
    }

    @Test
    @DisplayName("Test changeBorneStateWithId - borne inexistante")
    void changeBorneStateWithIdBorneInexistante() {
        long borneId = 1L;
        EtatBorne newState = EtatBorne.DISPONIBLE;
        when(borneRepository.findById(borneId)).thenReturn(Optional.empty());

        borneService.changeBorneStateWithId(borneId, newState);

        verify(borneRepository, times(1)).findById(borneId);
        verify(borneRepository, never()).save(any());
    }
}
