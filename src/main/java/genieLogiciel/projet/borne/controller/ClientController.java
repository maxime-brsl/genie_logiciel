package genieLogiciel.projet.borne.controller;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.repository.ClientRepository;
import genieLogiciel.projet.borne.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClient() {
        return clientService.getAllClients();
    }

    @PostMapping
    public Client createClient(@RequestBody Client utilisateur) {
        return clientService.createClient(utilisateur);
    }
}
