package by.SabinaGlinskaya.levon.controller;

import by.SabinaGlinskaya.levon.dto.ScooterDTO;
import by.SabinaGlinskaya.levon.exceptions.AccountAuthException;
import by.SabinaGlinskaya.levon.exceptions.ScooterValidationException;
import by.SabinaGlinskaya.levon.model.Account;
import by.SabinaGlinskaya.levon.model.AccountScooter;
import by.SabinaGlinskaya.levon.model.Scooter;
import by.SabinaGlinskaya.levon.services.AccountScooterService;
import by.SabinaGlinskaya.levon.services.AccountService;
import by.SabinaGlinskaya.levon.services.ScooterService;
import by.SabinaGlinskaya.levon.validation.ScooterValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    @Autowired
    private ScooterService scooterService;

    @Autowired
    ScooterValidator scooterValidator;

    @PostMapping(value = "addScooter")
    public ResponseEntity<Scooter> addScooter(@Valid @RequestBody ScooterDTO scooterDTO, BindingResult errors)
    {
        scooterValidator.validate(scooterDTO, errors);

        if(errors.hasErrors()) {
            throw new ScooterValidationException(errors);
        }

        Scooter scooter = scooterDTO.toScooter();

        scooterService.create(scooter);
        log.info("Post request : /api/v1/admin/addProduct");
        return new ResponseEntity<>(scooter, HttpStatus.CREATED);
    }
}
