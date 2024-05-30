package genieLogiciel.projet.borne.menu;


import genieLogiciel.projet.borne.service.BorneService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateReservationMenuTests {

    @Mock
    private BorneService borneService;

    @InjectMocks
    private CreateReservationMenu createReservationMenu;


}
