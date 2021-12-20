package by.SabinaGlinskaya.levon.controller;

import by.SabinaGlinskaya.levon.config.security.jwt.JwtTokenProvider;
import by.SabinaGlinskaya.levon.dto.AuthAccountDTO;
import by.SabinaGlinskaya.levon.exceptions.AccountAuthException;
import by.SabinaGlinskaya.levon.exceptions.AccountValidationException;
import by.SabinaGlinskaya.levon.model.Account;
import by.SabinaGlinskaya.levon.services.AccountService;
import by.SabinaGlinskaya.levon.validation.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/account")
public class AccountController {
    @Autowired
    private AccountValidator accountValidator;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestParam Map<String, String> mapParam) {
        Long accountId = Long.parseLong(mapParam.get("accountId"));
        Account account = accountService.findById(accountId);
        Boolean isAdmin = "ROLE_ADMIN".equals(account.getRole().getName());
        return new ResponseEntity<>(isAdmin, HttpStatus.OK);
    }
}
