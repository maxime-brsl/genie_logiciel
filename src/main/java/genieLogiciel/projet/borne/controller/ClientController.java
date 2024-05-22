package genieLogiciel.projet.borne.controller;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAllUtilisateurs() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Client createUtilisateur(@RequestBody Client utilisateur) {
        return clientRepository.save(utilisateur);
    }
}
