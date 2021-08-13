package com.example.authentication_with_jwt.custom_exception;

import org.springframework.security.core.AuthenticationException;

public class UserIdNotFound extends AuthenticationException {
    public UserIdNotFound(String msg) {
        super(msg);
    }
}
