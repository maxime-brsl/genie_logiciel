package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    public boolean isLicensePlateInDatabase(String licensePlate) {
        return vehiculeRepository.findByplaqueImmatriculation(licensePlate).isPresent();
    }

    public Long getVehiculeIdByLicensePlate(final String licensePlate) {
        return vehiculeRepository.findByplaqueImmatriculation(licensePlate)
                .map(vehicule -> vehicule.getId())
                .orElse(null);
    }
}
