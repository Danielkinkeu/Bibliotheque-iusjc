package org.isj.ing3.isi.webservice.webservicerest.model.modeletat;


import lombok.Data;

@Data
public class Connection {
    private String login;
    private String password;
    private String codeErreur;
}
