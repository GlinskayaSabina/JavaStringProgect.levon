package by.SabinaGlinskaya.levon.services;

import by.SabinaGlinskaya.levon.model.Scooter;
import by.SabinaGlinskaya.levon.repository.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService {
    @Autowired
    ScooterRepository scooterRepository;

    public List<Scooter> getAll() {
        return scooterRepository.findAll();
    }

    public Scooter getById(Long id) {
        Scooter scooter = scooterRepository.findScooterById(id);
        return scooter;
    }
}
