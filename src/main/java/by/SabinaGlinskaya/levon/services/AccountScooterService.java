package by.SabinaGlinskaya.levon.services;

import by.SabinaGlinskaya.levon.model.AccountScooter;
import by.SabinaGlinskaya.levon.model.Scooter;
import by.SabinaGlinskaya.levon.repository.AccountScooterRepository;
import by.SabinaGlinskaya.levon.repository.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountScooterService {
    @Autowired
    AccountScooterRepository accountScooterRepository;

    public void save(AccountScooter accountScooter) {
        accountScooterRepository.save(accountScooter);
    }
}
