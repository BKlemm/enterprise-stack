package com.avondock.app.system.iam.auth.response;

import lombok.Getter;

public class JWTTokenResponse {

    @Getter
    private final String token;

    public JWTTokenResponse(String generateToken) {
        this.token = generateToken;
    }
}
