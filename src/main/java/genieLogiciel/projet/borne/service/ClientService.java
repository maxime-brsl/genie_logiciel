package genielogiciel.projet.borne.service;

import genielogiciel.projet.borne.entity.Client;
import genielogiciel.projet.borne.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void addClient(final Client client) {
        clientRepository.save(client);
    }

    public boolean isPhoneNumberInDatabase(final String phoneNumber) {
        return clientRepository.findBynumeroTel(phoneNumber).isPresent();
    }

    public boolean verifierMotDePasse(final String phoneNumber, final String motDePasseUtilisateur) {
        Client client = clientRepository.findBynumeroTel(phoneNumber).orElse(null);
        if (client == null) {
            return false;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(motDePasseUtilisateur, client.getMotDePasse());
    }

    public Client getClientByPhoneNumber(final String phoneNumber) {
        return clientRepository.findBynumeroTel(phoneNumber).orElse(null);
    }
}