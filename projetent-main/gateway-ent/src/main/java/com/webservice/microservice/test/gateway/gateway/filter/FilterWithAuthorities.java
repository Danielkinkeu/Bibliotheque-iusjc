package com.webservice.microservice.test.gateway.gateway.filter;


import com.webservice.microservice.test.gateway.gateway.exception.IsjException;
import org.springframework.http.HttpStatus;

public class FilterWithAuthorities {

    public static void filterAuthirities(String path, String role) throws IsjException {
        System.out.println(path);
        System.out.println(role);
        if(path.contains("gest-note") && (path.contains("save") || path.contains("update") || path.contains("delete")) && (role.equals("Etudiant") || role.equals("etudiant")) ) {
            throw new IsjException("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
    }
}
