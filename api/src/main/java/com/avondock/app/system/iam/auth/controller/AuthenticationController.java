package com.avondock.app.system.iam.auth.controller;


import com.avondock.app.system.iam.auth.request.AuthenticationData;
import com.avondock.app.system.iam.auth.request.RegistrationData;
import com.avondock.app.system.iam.auth.service.AuthenticationService;
import com.avondock.app.system.iam.auth.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping
public class AuthenticationController {

    private final AuthenticationService authService;
    private final RegistrationService   registerService;

    @Autowired
    public AuthenticationController(AuthenticationService authService, RegistrationService registerService) {
        this.authService = authService;
        this.registerService = registerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createCustomer(@RequestBody AuthenticationData data) {
        return new ResponseEntity<>(authService.generateJWTToken(data.getUserName(), data.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegistrationData data) {
        registerService.register(data.getFirstName(), data.getLastName(), data.getUsername(), data.getPassword());
        return new ResponseEntity<>(data.getUsername() + " created", HttpStatus.CREATED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
