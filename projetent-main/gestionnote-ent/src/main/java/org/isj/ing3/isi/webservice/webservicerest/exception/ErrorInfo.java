package org.isj.ing3.isi.webservice.webservicerest.exception;

import lombok.Getter;
import org.zalando.problem.Status;

@Getter
public enum ErrorInfo {

    REFERENCE_RESSOURCE_REQUIRED("#REFERENCE_RESSOURCE_REQUIRED", Status.BAD_REQUEST),
    RESSOURCE_NOT_FOUND("#RESSOURCE_NOT_FOUND", Status.NOT_FOUND),
    REFERENCE_RESSOURCE_ALREADY_USED("#REFERENCE_RESSOURCE_ALREADY_USED", Status.BAD_REQUEST);

    private final String message;
    private final Status httpStatus;

    ErrorInfo(String message, Status httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
