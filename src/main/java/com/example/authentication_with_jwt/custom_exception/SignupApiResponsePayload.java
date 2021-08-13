package com.example.authentication_with_jwt.custom_exception;

public class SignupApiResponsePayload extends ApiResponsePayload{
    private final long unixTime = System.currentTimeMillis();

    public SignupApiResponsePayload() {
    }

    public SignupApiResponsePayload(String message) {
        super(message);
    }

    public long getUnixTime() {
        return unixTime;
    }
}
