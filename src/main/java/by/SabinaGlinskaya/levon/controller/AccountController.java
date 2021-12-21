package by.SabinaGlinskaya.levon.controller;

import by.SabinaGlinskaya.levon.exceptions.AccountAuthException;
import by.SabinaGlinskaya.levon.model.Account;
import by.SabinaGlinskaya.levon.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestParam Map<String, String> mapParam) {
        Long accountId = Long.parseLong(mapParam.get("accountId"));
        Account account = accountService.findById(accountId);
        if(account != null) {
            Boolean isAdmin = "ROLE_ADMIN".equals(account.getRole().getName());
            return new ResponseEntity<>(isAdmin, HttpStatus.OK);
        }
        else throw new AccountAuthException("Account not found!");
    }
}
