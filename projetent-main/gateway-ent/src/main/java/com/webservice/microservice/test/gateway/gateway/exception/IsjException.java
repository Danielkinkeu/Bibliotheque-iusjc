package com.webservice.microservice.test.gateway.gateway.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * The Class HotelTechnicalException.
 */
public class IsjException extends Exception {

    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
    private List<String> messages;


    public IsjException(ErrorInfo errorInfo, String... messages) {
        super(errorInfo.getMessage());
        this.httpStatus = errorInfo.getHttpStatus();
        this.messages = Arrays.asList(messages);
    }

    /**
     * @param msg
     * @param httpStatus
     */
    public IsjException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    /**
     * @param cause
     * @param httpStatus
     */
    public IsjException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
