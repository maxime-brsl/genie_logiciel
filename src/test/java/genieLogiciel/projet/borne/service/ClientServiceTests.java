package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getAllClients - aucun client")
    void testGetAllClientsAucunClient() {
        when(clientRepository.findAll()).thenReturn(new ArrayList<>());
        List<Client> clients = clientService.getAllClients();
        assertTrue(clients.isEmpty(), "La liste des clients devrait être vide");
    }

    @Test
    @DisplayName("Test getAllClients - un client")
    void testGetAllClientsUnClient() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        when(clientRepository.findAll()).thenReturn(clients);
        List<Client> retrievedClients = clientService.getAllClients();
        assertEquals(1, retrievedClients.size(), "La liste des clients devrait contenir un client");
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
    void testIsPhoneNumberInDatabasePresent() {
        String phoneNumber = "1234567890";
        when(clientRepository.findBynumeroTel(phoneNumber)).thenReturn(Optional.of(new Client()));
        assertTrue(clientService.isPhoneNumberInDatabase(phoneNumber), "Le numéro de téléphone devrait être présent dans la base de données");
    }

    @Test
    @DisplayName("Test isPhoneNumberInDatabase - numéro de téléphone absent")
    void testIsPhoneNumberInDatabaseAbsent() {
        String phoneNumber = "1234567890";
        when(clientRepository.findBynumeroTel(phoneNumber)).thenReturn(Optional.empty());
        assertFalse(clientService.isPhoneNumberInDatabase(phoneNumber), "Le numéro de téléphone devrait être absent de la base de données");
    }

    @Test
    @DisplayName("Test isPhoneNumberInDatabase - numéro de téléphone null")
    void testIsPhoneNumberInDatabaseNull() {
        assertFalse(clientService.isPhoneNumberInDatabase(null), "Le numéro de téléphone ne devrait pas être présent dans la base de données");
    }

    @Test
    @DisplayName("Test isPhoneNumberInDatabase - numéro de téléphone mauvais format")
    void testIsPhoneNumberInDatabaseMauvaisFormat() {
        assertFalse(clientService.isPhoneNumberInDatabase("abcde"), "Le numéro de téléphone ne devrait pas être présent dans la base de données");
    }

    @Test
    @DisplayName("Test verifierMotDePasse - mot de passe correct")
    void testVerifierMotDePasseCorrect() {
        String phoneNumber = "+33680702581";
        String motDePasse = "password";
        Client client = new Client();
        client.setMotDePasse(motDePasse);
        when(clientRepository.findBynumeroTel(phoneNumber)).thenReturn(Optional.of(client));
        assertTrue(clientService.verifierMotDePasse(phoneNumber, motDePasse), "Le mot de passe devrait être correct");
    }

    @Test
    @DisplayName("Test verifierMotDePasse - numéro de téléphone non trouvé")
    void testVerifierMotDePasseNumeroNonTrouve() {
        String phoneNumber = "1234567890";
        String motDePasse = "password";
        when(clientRepository.findBynumeroTel(phoneNumber)).thenReturn(Optional.empty());
        assertFalse(clientService.verifierMotDePasse(phoneNumber, motDePasse), "Le numéro de téléphone ne devrait pas être trouvé");
    }

    @Test
    @DisplayName("Test verifierMotDePasse - mot de passe incorrect")
    void testVerifierMotDePasseIncorrect() {
        String phoneNumber = "1234567890";
        String motDePasse = "password";
        Client client = new Client();
        client.setMotDePasse("wrongpassword");
        when(clientRepository.findBynumeroTel(phoneNumber)).thenReturn(Optional.of(client));
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
        when(clientRepository.findBynumeroTel(phoneNumber)).thenReturn(Optional.of(client));
        assertEquals(client, clientService.getClientByPhoneNumber(phoneNumber), "Le client devrait être trouvé");
    }

    @Test
    @DisplayName("Test getClientByPhoneNumber - numéro de téléphone absent")
    void testGetClientByPhoneNumberAbsent() {
        String phoneNumber = "+33680702581";
        when(clientRepository.findBynumeroTel(phoneNumber)).thenReturn(Optional.empty());
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
}
