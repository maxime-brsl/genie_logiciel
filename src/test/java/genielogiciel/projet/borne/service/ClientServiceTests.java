package genielogiciel.projet.borne.service;

import genielogiciel.projet.borne.entity.Client;
import genielogiciel.projet.borne.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTests {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test addClient")
    void testAddClient() {
        Client client = new Client();
        clientService.addClient(client);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    @DisplayName("Test isPhoneNumberInDatabase - numéro de téléphone présent")
    void testIsPhoneNumberExistsPresent() {
        String phoneNumber = "1234567890";
        when(clientRepository.findBynumeroTelephone(phoneNumber)).thenReturn(Optional.of(new Client()));
        assertTrue(clientService.isPhoneNumberExists(phoneNumber), "Le numéro de téléphone devrait être présent dans la base de données");
    }

    @Test
    @DisplayName("Test isPhoneNumberInDatabase - numéro de téléphone absent")
    void testIsPhoneNumberExistsAbsent() {
        String phoneNumber = "1234567890";
        when(clientRepository.findBynumeroTelephone(phoneNumber)).thenReturn(Optional.empty());
        assertFalse(clientService.isPhoneNumberExists(phoneNumber), "Le numéro de téléphone devrait être absent de la base de données");
    }

    @Test
    @DisplayName("Test isPhoneNumberInDatabase - numéro de téléphone null")
    void testIsPhoneNumberExistsNull() {
        assertFalse(clientService.isPhoneNumberExists(null), "Le numéro de téléphone ne devrait pas être présent dans la base de données");
    }

    @Test
    @DisplayName("Test isPhoneNumberInDatabase - numéro de téléphone mauvais format")
    void testIsPhoneNumberExistsMauvaisFormat() {
        assertFalse(clientService.isPhoneNumberExists("abcde"), "Le numéro de téléphone ne devrait pas être présent dans la base de données");
    }

    @Test
    @DisplayName("Test verifierMotDePasse - mot de passe correct")
    void testVerifierMotDePasseCorrect() {
        String phoneNumber = "+33680702581";
        String motDePasse = "password";
        Client client = new Client();
        client.setMotDePasse(motDePasse);
        when(clientRepository.findBynumeroTelephone(phoneNumber)).thenReturn(Optional.of(client));
        assertTrue(clientService.verifierMotDePasse(phoneNumber, motDePasse), "Le mot de passe devrait être correct");
    }

    @Test
    @DisplayName("Test verifierMotDePasse - numéro de téléphone non trouvé")
    void testVerifierMotDePasseNumeroNonTrouve() {
        String phoneNumber = "1234567890";
        String motDePasse = "password";
        when(clientRepository.findBynumeroTelephone(phoneNumber)).thenReturn(Optional.empty());
        assertFalse(clientService.verifierMotDePasse(phoneNumber, motDePasse), "Le numéro de téléphone ne devrait pas être trouvé");
    }

    @Test
    @DisplayName("Test verifierMotDePasse - mot de passe incorrect")
    void testVerifierMotDePasseIncorrect() {
        String phoneNumber = "1234567890";
        String motDePasse = "password";
        Client client = new Client();
        client.setMotDePasse("wrongpassword");
        when(clientRepository.findBynumeroTelephone(phoneNumber)).thenReturn(Optional.of(client));
        assertFalse(clientService.verifierMotDePasse(phoneNumber, motDePasse), "Le mot de passe devrait être incorrect");
    }

    @Test
    @DisplayName("Test setNumeroDebit - numéro de débit correctement encodé")
    void testSetNumeroDebitEncodageCorrect() {
        String numeroDebit = "1234123412341234 555 02/26";
        Client client = new Client();
        client.setNumeroDebit(numeroDebit);
        assertTrue(encoder.matches(numeroDebit, client.getNumeroDebit()), "Le numéro de débit devrait être correctement encodé");
    }

    @Test
    @DisplayName("Test setNumeroDebit - mot de passe mal encodé")
    void testSetNumeroDebitEncodageIncorrect() {
        String numeroDebit = "1234123412341234 555 02/26";
        Client client = new Client();
        client.setNumeroDebit(numeroDebit);
        assertFalse(encoder.matches("wrongnumber", client.getNumeroDebit()), "Le numéro de débit ne devrait pas être correctement encodé");
    }

    @Test
    @DisplayName("Test getClientByPhoneNumber - numéro de téléphone présent")
    void testGetClientByPhoneNumberPresent() {
        String phoneNumber = "+33680702581";
        Client client = new Client();
        when(clientRepository.findBynumeroTelephone(phoneNumber)).thenReturn(Optional.of(client));
        assertEquals(client, clientService.getClientByPhoneNumber(phoneNumber), "Le client devrait être trouvé");
    }

    @Test
    @DisplayName("Test getClientByPhoneNumber - numéro de téléphone absent")
    void testGetClientByPhoneNumberAbsent() {
        String phoneNumber = "+33680702581";
        when(clientRepository.findBynumeroTelephone(phoneNumber)).thenReturn(Optional.empty());
        assertNull(clientService.getClientByPhoneNumber(phoneNumber), "Le client ne devrait pas être trouvé");
    }

    @Test
    @DisplayName("Test getClientByPhoneNumber - numéro de téléphone null")
    void testGetClientByPhoneNumberNull() {
        assertNull(clientService.getClientByPhoneNumber(null), "Le client ne devrait pas être trouvé");
    }

    @Test
    @DisplayName("Test getClientByPhoneNumber - numéro de téléphone mauvais format")
    void testGetClientByPhoneNumberMauvaisFormat() {
        assertNull(clientService.getClientByPhoneNumber("abcde"), "Le client ne devrait pas être trouvé");
    }

    @Test
    @DisplayName("Test updateClient")
    void testUpdateClient() {
        Client client = new Client(1L, "Nom", "Prenom", "mail", "1 adresse, 11111 ville, pays", "1234123412341234 555 01/01", "+33123456789", "motDePasse");
        clientService.addClient(client);
        client.setNumeroTelephone("+33612345678");

        clientService.updateClient(client);

        assertEquals("+33612345678", client.getNumeroTelephone());
    }
}
