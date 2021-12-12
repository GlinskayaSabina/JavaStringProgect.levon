package by.SabinaGlinskaya.levon.services;

import by.SabinaGlinskaya.levon.model.Account;
import by.SabinaGlinskaya.levon.model.Role;
import by.SabinaGlinskaya.levon.repository.AccountRepository;
import by.SabinaGlinskaya.levon.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account findByLogin(String login) {
        return accountRepository.findAccountByLogin(login);
    }

    public Account findByLoginAndPassword(String login, String password) {
        Account account = findByLogin(login);
        if(account != null) {
            if(passwordEncoder.matches(password, account.getPassword())) {
                return account;
            }
        }
        return null;
    }

    public Account findById(Long id) { return accountRepository.findAccountById(id); }

    public Account signup(Account account) {
        Role role = roleRepository.findRoleByName("ROLE_BUYER");
        account.setRole(role);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }
}
