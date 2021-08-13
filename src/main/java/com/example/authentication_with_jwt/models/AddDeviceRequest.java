package com.example.authentication_with_jwt.models;

import java.io.Serializable;

public class AddDeviceRequest implements Serializable {
    private String seri;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }
}
