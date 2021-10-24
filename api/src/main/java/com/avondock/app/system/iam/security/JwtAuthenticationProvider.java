package com.avondock.app.system.iam.security;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtAuthenticationTokenService service;

    @Autowired
    public JwtAuthenticationProvider(JwtAuthenticationTokenService jwtService) {
        this.service = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("HERE IAM");
        try {
            String token    = (String) authentication.getCredentials();
            String userName = service.getUserNameFromToken(token);

            return service.validateToken(token)
                    .map(isTrue -> new JwtAuthenticatedProfile(userName))
                    .orElseThrow(() -> new JwtAuthenticationException("JWT Token validation failed"));
        } catch (JwtException ex) {
            throw new JwtAuthenticationException("Failed to verify token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
