package by.SabinaGlinskaya.levon.repository;

import by.SabinaGlinskaya.levon.model.AccountScooter;
import by.SabinaGlinskaya.levon.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountScooterRepository extends JpaRepository<AccountScooter, Long> {
}
