package com.example.authentication_with_jwt.models;

public class LoginResponsePayload {
    private String jwt;

    public LoginResponsePayload() {
    }

    public LoginResponsePayload(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
