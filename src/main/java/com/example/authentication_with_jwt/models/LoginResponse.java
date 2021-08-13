package com.example.authentication_with_jwt.models;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private final String jwt;
    private final String message;

    public LoginResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public LoginResponse(String message) {
        this.message = message;
        this.jwt = "";
    }

    public String getMessage() {
        return message;
    }

    public String getJwt() {
        return jwt;
    }
}
