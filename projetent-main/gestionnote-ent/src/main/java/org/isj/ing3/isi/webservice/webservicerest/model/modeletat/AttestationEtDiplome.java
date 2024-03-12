package org.isj.ing3.isi.webservice.webservicerest.model.modeletat;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttestationEtDiplome implements Serializable {
    private int annee;
    private String dateJury;
    private Long codeClasse;
    private String codeUser;
    private String typeEtat;
}
