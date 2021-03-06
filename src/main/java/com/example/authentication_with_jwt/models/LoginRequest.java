package com.example.authentication_with_jwt.models;

import org.apache.juli.logging.Log;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    private String username;
    private String password;

    public LoginRequest() {

    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
