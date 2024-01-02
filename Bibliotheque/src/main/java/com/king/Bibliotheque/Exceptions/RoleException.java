package com.king.Bibliotheque.Exceptions;

public class RoleException extends RuntimeException {
    public RoleException(String str) {
        // calling the constructor of parent Exception
        super(str);
    }
}
