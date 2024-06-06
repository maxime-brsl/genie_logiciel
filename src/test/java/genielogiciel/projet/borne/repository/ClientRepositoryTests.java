package genielogiciel.projet.borne.repository;

import genielogiciel.projet.borne.entity.Client;
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
class ClientRepositoryTests {

    @Mock
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Test findByNumeroTel - client trouvé")
    void testFindByNumeroTelephoneWhenClientExists() {
        Client client = new Client();
        client.setId(1L);
        client.setNumeroTelephone("123456789");
        when(clientRepository.findBynumeroTelephone("123456789")).thenReturn(Optional.of(client));

        Optional<Client> foundClient = clientRepository.findBynumeroTelephone("123456789");

        assertTrue(foundClient.isPresent());
        assertEquals(client, foundClient.get());
    }

    @Test
    @DisplayName("Test findByNumeroTel - aucun client trouvé")
    void testFindByNumeroTelephoneWhenNoClientExists() {
        when(clientRepository.findBynumeroTelephone("987654321")).thenReturn(Optional.empty());

        Optional<Client> foundClient = clientRepository.findBynumeroTelephone("987654321");

        assertTrue(foundClient.isEmpty());
    }
}
