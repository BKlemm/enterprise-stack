package com.avondock.app.system.iam.auth.service;

import com.avondock.app.system.iam.auth.model.Account;
import com.avondock.app.system.iam.auth.model.AccountId;
import com.avondock.app.system.iam.auth.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AccountRepository repository;
    private final PasswordEncoder   passwordEncoder;

    @Autowired
    public RegistrationService(AccountRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String firstName, String lastName, String username, String password) {
        Account account = new Account(
                new AccountId(),
                firstName,
                lastName,
                username,
                passwordEncoder.encode(password)
        );

        repository.save(account);
    }
}
