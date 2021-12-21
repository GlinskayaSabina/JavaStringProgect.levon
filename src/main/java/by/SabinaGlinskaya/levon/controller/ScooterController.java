package by.SabinaGlinskaya.levon.controller;

import by.SabinaGlinskaya.levon.dto.RentScooterDTO;
import by.SabinaGlinskaya.levon.exceptions.ScooterException;
import by.SabinaGlinskaya.levon.model.Account;
import by.SabinaGlinskaya.levon.model.AccountScooter;
import by.SabinaGlinskaya.levon.model.Scooter;
import by.SabinaGlinskaya.levon.services.AccountScooterService;
import by.SabinaGlinskaya.levon.services.AccountService;
import by.SabinaGlinskaya.levon.services.ScooterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/scooter")
public class ScooterController {
    @Autowired
    private ScooterService scooterService;

    @Autowired
    private AccountScooterService accountScooterService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "list")
    public ResponseEntity<List<Scooter>> getScooters() {
        List<Scooter> scooters = scooterService.getAll();
        log.info("Get query : /api/v1/scooter/list");
        return new ResponseEntity<>(scooters, HttpStatus.OK);
    }

    @GetMapping(value = "get")
    public ResponseEntity<Scooter> getScooter(@RequestParam Map<String, String> mapParam) {
        Long scooterId = Long.parseLong(mapParam.get("id"));
        Scooter scooter = scooterService.getById(scooterId);
        if(scooter == null) {
            throw new ScooterException("Scooter with id " + scooterId + " not found!");
        }
        return new ResponseEntity<>(scooter, HttpStatus.OK);
    }

    @PostMapping(value = "rent")
    public ResponseEntity rentScooter(RequestEntity<RentScooterDTO> rentScooterDTO) {
        Account account = accountService.findById(rentScooterDTO.getBody().getAccountId());
        Scooter scooter = scooterService.getById(rentScooterDTO.getBody().getScooterId());
        AccountScooter accountScooter = new AccountScooter(scooter, account);
        accountScooterService.save(accountScooter);
        return new ResponseEntity(HttpStatus.OK);
    }
}
