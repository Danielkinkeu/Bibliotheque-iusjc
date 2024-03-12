package org.isj.ing3.isi.webservice.webservicerest.model.modeletat;

import lombok.Data;

import java.io.Serializable;

@Data
public class Pv implements Serializable {
    private String semestre;
    private Long codeClasse;
    private String annee;
    private String codeUser;
    private boolean format;
}
