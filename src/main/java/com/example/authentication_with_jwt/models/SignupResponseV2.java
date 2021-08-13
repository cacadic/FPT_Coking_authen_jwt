package com.example.authentication_with_jwt.models;

import java.io.Serializable;

public class SignupResponseV2 implements Serializable {
    private Boolean success;

    private SignupResponsePayload payload;

    public SignupResponseV2() {
    }

    public SignupResponseV2(Boolean success, SignupResponsePayload payload) {
        this.success = success;
        this.payload = payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public SignupResponsePayload getPayload() {
        return payload;
    }

    public void setPayload(SignupResponsePayload payload) {
        this.payload = payload;
    }
}
