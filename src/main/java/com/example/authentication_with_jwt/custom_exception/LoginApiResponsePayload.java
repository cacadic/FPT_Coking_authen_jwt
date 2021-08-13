package com.example.authentication_with_jwt.custom_exception;

public class LoginApiResponsePayload extends ApiResponsePayload{
    private final long unixTime = System.currentTimeMillis();

    public LoginApiResponsePayload() {
    }

    public LoginApiResponsePayload(String message) {
        super(message);
    }

    public long getUnixTime() {
        return unixTime;
    }
}
