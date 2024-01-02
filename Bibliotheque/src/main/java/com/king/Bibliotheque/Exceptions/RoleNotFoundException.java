package com.king.Bibliotheque.Exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String str) {
        // calling the constructor of parent Exception
        super(str);
    }
}
