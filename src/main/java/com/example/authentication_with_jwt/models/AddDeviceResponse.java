package com.example.authentication_with_jwt.models;

import java.io.Serializable;

public class AddDeviceResponse implements Serializable {
    private String message;

    public AddDeviceResponse() {
    }

    public AddDeviceResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
