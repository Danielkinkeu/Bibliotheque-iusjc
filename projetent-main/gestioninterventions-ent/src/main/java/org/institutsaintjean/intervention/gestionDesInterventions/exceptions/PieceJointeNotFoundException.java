package org.institutsaintjean.intervention.gestionDesInterventions.exceptions;

public class PieceJointeNotFoundException extends RuntimeException {

    public PieceJointeNotFoundException(String message) {
        super(message);
    }

    public PieceJointeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}