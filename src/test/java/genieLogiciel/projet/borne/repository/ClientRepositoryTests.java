package genieLogiciel.projet.borne.repository;

import genieLogiciel.projet.borne.entity.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientRepositoryTests {

    @Mock
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Test findByNumeroTel - client trouvé")
    void testFindByNumeroTelWhenClientExists() {
        Client client = new Client();
        client.setId(1L);
        client.setNumeroTel("123456789");
        when(clientRepository.findBynumeroTel("123456789")).thenReturn(Optional.of(client));

        Optional<Client> foundClient = clientRepository.findBynumeroTel("123456789");

        assertTrue(foundClient.isPresent());
        assertEquals(client, foundClient.get());
    }

    @Test
    @DisplayName("Test findByNumeroTel - aucun client trouvé")
    void testFindByNumeroTelWhenNoClientExists() {
        when(clientRepository.findBynumeroTel("987654321")).thenReturn(Optional.empty());

        Optional<Client> foundClient = clientRepository.findBynumeroTel("987654321");

        assertTrue(foundClient.isEmpty());
    }
}
