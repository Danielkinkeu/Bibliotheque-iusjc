package com.webservice.microservice.test.gateway.gateway.exception;

import org.springframework.http.HttpStatus;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
    public JwtTokenMissingException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
