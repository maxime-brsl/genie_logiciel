package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public boolean isPhoneNumberInDatabase(final String phoneNumber) {
        return clientRepository.findBynumeroTel(phoneNumber).isPresent();
    }

    public boolean verifierMotDePasse(String phoneNumber, String motDePasseUtilisateur) {
        Client client = clientRepository.findBynumeroTel(phoneNumber).orElse(null);
        if (client == null) {
            return false;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(motDePasseUtilisateur, client.getMotDePasse());
    }
}