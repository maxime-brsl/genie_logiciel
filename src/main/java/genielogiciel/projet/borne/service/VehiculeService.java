package genielogiciel.projet.borne.service;

import genielogiciel.projet.borne.entity.Client;
import genielogiciel.projet.borne.entity.Vehicule;
import genielogiciel.projet.borne.repository.VehiculeRepository;
import genielogiciel.projet.borne.util.LicensePlateValidator;
import genielogiciel.projet.borne.util.TextMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.logging.Logger;

@Service
public class VehiculeService {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = Logger.getLogger(String.valueOf(VehiculeService.class));

    private final VehiculeRepository vehiculeRepository;

    @Autowired
    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    /**
     * Vérifier si une plaque d'immatriculation existe
     *
     * @param licensePlate plaque d'immatriculation
     * @return true si la plaque d'immatriculation existe, false sinon
     */
    public boolean isLicenseplateExist(final String licensePlate) {
        return vehiculeRepository.findByplaqueImmatriculation(licensePlate).isPresent();
    }

    /**
     * Récupérer l'id d'un véhicule par sa plaque d'immatriculation
     *
     * @param licensePlate plaque d'immatriculation
     * @return l'id du véhicule
     */
    public Long getVehiculeIdByLicensePlate(final String licensePlate) {
        return vehiculeRepository.findByplaqueImmatriculation(licensePlate)
                .map(Vehicule::getId)
                .orElse(null);
    }

    /**
     * Ajouter un véhicule
     *
     * @param vehicule le véhicule
     */
    public void addVehicule(final Vehicule vehicule) {
        vehiculeRepository.save(vehicule);
    }

    /**
     * Ajouter un véhicule à un client
     *
     * @param licensePlate plaque d'immatriculation
     * @param client       le client
     */
    public void addVehiculeToClient(String licensePlate, final Client client) {
        Vehicule vehicule = new Vehicule();
        if (licensePlate == null) {
            LOG.info(TextMenu.SAISIR_NUMERO_IMMATRICULATION);
            licensePlate = scanner.nextLine();
            while (!LicensePlateValidator.isValidLicensePlate(licensePlate)) {
                LOG.info(TextMenu.IMMATRICULATION_INVALIDE);
                LOG.info(TextMenu.SAISIR_NUMERO_IMMATRICULATION);
                licensePlate = scanner.nextLine();
            }
        }
        vehicule.setPlaqueImmatriculation(licensePlate);
        Long idClient = client.getId();
        vehicule.setClientId(idClient);
        LOG.info("Ce véhicule est-il loué ? (O/N)");
        vehicule.setEstLoue(scanner.nextLine().equalsIgnoreCase("O"));
        addVehicule(vehicule);
        LOG.info("Véhicule ajouté avec succès !");
    }
}