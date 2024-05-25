package genieLogiciel.projet.borne.service;

import genieLogiciel.projet.borne.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    public boolean isLicensePlateInDatabase(String licensePlate) {
        return vehiculeRepository.findByLicensePlate(licensePlate).isPresent();
    }
}
