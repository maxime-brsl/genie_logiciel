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

    /**
     * Ajouter un client
     *
     * @param client le client
     */
    public void addClient(final Client client) {
        clientRepository.save(client);
    }

    /**
     * Vérifier si un numéro de téléphone existe
     *
     * @param phoneNumber le numéro de téléphone
     * @return true si le numéro de téléphone existe, false sinon
     */
    public boolean isPhoneNumberExist(final String phoneNumber) {
        return clientRepository.findBynumeroTelephone(phoneNumber).isPresent();
    }

    /**
     * Vérifier si un mot de passe est correct
     *
     * @param phoneNumber          le numéro de téléphone
     * @param motDePasseUtilisateur le mot de passe
     * @return true si le mot de passe est correct, false sinon
     */
    public boolean verifierMotDePasse(final String phoneNumber, final String motDePasseUtilisateur) {
        Client client = clientRepository.findBynumeroTelephone(phoneNumber).orElse(null);
        if (client == null) {
            return false;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(motDePasseUtilisateur, client.getMotDePasse());
    }

    /**
     * Récupérer un client par son numéro de téléphone
     *
     * @param phoneNumber le numéro de téléphone
     * @return le client
     */
    public Client getClientByPhoneNumber(final String phoneNumber) {
        return clientRepository.findBynumeroTelephone(phoneNumber).orElse(null);
    }
}