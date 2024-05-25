package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public boolean isPhoneNumberInDatabase(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber).isPresent();
    }
}