package com.example.authentication_with_jwt.models;

public class SignupResponsePayload {
    private String jwt;

    public SignupResponsePayload() {
    }

    public SignupResponsePayload(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
