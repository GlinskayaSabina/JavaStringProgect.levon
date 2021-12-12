package by.SabinaGlinskaya.levon.config.security;

import by.SabinaGlinskaya.levon.config.security.jwt.JwtAccount;
import by.SabinaGlinskaya.levon.model.Account;
import by.SabinaGlinskaya.levon.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public JwtAccount loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.findByLogin(login);
        return JwtAccount.fromAccountToJwtAccount(account);
    }
}
