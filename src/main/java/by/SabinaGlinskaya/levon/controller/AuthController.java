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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    @Autowired
    private AccountValidator accountValidator;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> signUp(@Valid @RequestBody AuthAccountDTO accountDetails, BindingResult errors)
    {
        accountValidator.validate(accountDetails, errors);

        if(errors.hasErrors()) {
            throw new AccountValidationException(errors);
        }

        Account account = accountDetails.ToAccount();
        accountService.signup(account);
//        log.info("Get request : /api/v1/auth/registerStudent");
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signIn(@RequestBody AuthAccountDTO accountDetails) {
        String login = accountDetails.getLogin();
        String password = accountDetails.getPassword();
        Account account = accountService.findByLoginAndPassword(login, password);
        if(account == null) throw new AccountAuthException("Wrong Credentials!");
        String token = jwtTokenProvider.createToken(login);
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("accountId", account.getId());
        response.put("role", account.getRole().getName());
        return ResponseEntity.ok(response);
    }
}
