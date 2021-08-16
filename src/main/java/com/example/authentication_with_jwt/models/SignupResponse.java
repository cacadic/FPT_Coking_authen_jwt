package com.example.authentication_with_jwt.models;

public class SignupResponse {
    private boolean success;

    public SignupResponse() {
    }

    public SignupResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
