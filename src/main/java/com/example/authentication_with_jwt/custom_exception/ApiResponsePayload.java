package com.example.authentication_with_jwt.custom_exception;

public class ApiResponsePayload {
    private String message;

    public ApiResponsePayload() {
    }

    public ApiResponsePayload(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
