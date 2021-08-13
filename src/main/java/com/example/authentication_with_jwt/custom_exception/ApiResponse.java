package com.example.authentication_with_jwt.custom_exception;

import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {
    private final Boolean success = false;
    private T payload;

    public ApiResponse() {
    }

    public ApiResponse(T payload) {
        this.payload = payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public ResponseEntity createResponse(T payload){
        ApiResponse<T> response = new ApiResponse<>();
        response.setPayload(payload);

        return ResponseEntity.ok().body(response);
    }

}
