package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.entity.Client;
import genieLogiciel.projet.borne.entity.Vehicule;
import genieLogiciel.projet.borne.repository.VehiculeRepository;
import genieLogiciel.projet.borne.util.LicensePlateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class VehiculeService {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private VehiculeRepository vehiculeRepository;

    public boolean isLicensePlateInDatabase(final String licensePlate) {
        return vehiculeRepository.findByplaqueImmatriculation(licensePlate).isPresent();
    }

    public Long getVehiculeIdByLicensePlate(final String licensePlate) {
        return vehiculeRepository.findByplaqueImmatriculation(licensePlate)
                .map(vehicule -> vehicule.getId())
                .orElse(null);
    }

    public void addVehicule(Vehicule vehicule) {
        vehiculeRepository.save(vehicule);
    }

    public void addLicensePlateToVehicule(String licensePlate, final Client client) {
        Vehicule vehicule = new Vehicule();
        if (licensePlate == null) {
            System.out.println("Saisir le numéro d'immatriculation : ");
            licensePlate = scanner.nextLine();
            while (!LicensePlateValidator.isValidLicensePlate(licensePlate)) {
                System.out.println("Le numéro d'immatriculation n'est pas valide.");
                System.out.println("Saisir le numéro d'immatriculation : ");
                licensePlate = scanner.nextLine();
            }
        }

        vehicule.setPlaqueImmatriculation(licensePlate);
        long idClient = client.getId();
        vehicule.setClientId(idClient);
        System.out.println("Ce véhicule est-il loué ? (O/N)");
        if (scanner.nextLine().equalsIgnoreCase("O")) {
            vehicule.setLoue(true);
        } else {
            vehicule.setLoue(false);
        }
        addVehicule(vehicule);
        System.out.println("Véhicule ajouté avec succès !");
    }
}