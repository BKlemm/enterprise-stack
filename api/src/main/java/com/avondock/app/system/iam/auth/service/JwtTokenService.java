package com.avondock.app.system.iam.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenService {

    private final String secret;
    private final Long   expiration;

    public JwtTokenService(@Value("secrets") String secret, @Value("604800") Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateToken(String userName) {
        final Date createdAt = new Date();
        final Date expiryAt  = calculateExpirationDate(createdAt);

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userName)
                .setIssuedAt(createdAt)
                .setExpiration(expiryAt)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdAt) {
        return new Date(createdAt.getTime() + expiration * 10000);
    }
}
