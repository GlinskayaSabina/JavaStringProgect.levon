package by.SabinaGlinskaya.levon.dto;

import by.SabinaGlinskaya.levon.model.Account;
import by.SabinaGlinskaya.levon.model.AccountScooter;
import by.SabinaGlinskaya.levon.model.Scooter;
import by.SabinaGlinskaya.levon.services.AccountService;
import by.SabinaGlinskaya.levon.services.ScooterService;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class RentScooterDTO {
    private Long accountId;

    private Long scooterId;
}
