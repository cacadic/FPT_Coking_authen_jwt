package com.example.authentication_with_jwt.custom_exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyException extends AuthenticationException {
    public UserAlreadyException(String msg) {
        super(msg);
    }
}
