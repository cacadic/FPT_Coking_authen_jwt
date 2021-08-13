package com.example.authentication_with_jwt.models;

import java.io.Serializable;

public class LoginResponseV2 implements Serializable {
    private Boolean success;
    private LoginResponsePayload payload;

    public LoginResponseV2() {
    }

    public LoginResponseV2(Boolean success, LoginResponsePayload payload) {
        this.success = success;
        this.payload = payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LoginResponsePayload getPayload() {
        return payload;
    }

    public void setPayload(LoginResponsePayload payload) {
        this.payload = payload;
    }
}
