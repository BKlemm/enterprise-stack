package com.avondock.app.system.iam.auth.service;

import com.avondock.app.system.iam.auth.repository.AccountRepository;
import com.avondock.app.system.iam.auth.response.JWTTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AuthenticationService {

    private final AccountRepository repository;
    private final JwtTokenService   jwtTokenService;
    private final PasswordEncoder   passwordEncoder;

    @Autowired
    public AuthenticationService(AccountRepository repository, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public JWTTokenResponse generateJWTToken(String userName, String password) {
        return repository.findOneByUserName(userName)
                .filter(account -> passwordEncoder.matches(password, account.getPassword()))
                .map(account -> new JWTTokenResponse(jwtTokenService.generateToken(userName)))
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }
}
