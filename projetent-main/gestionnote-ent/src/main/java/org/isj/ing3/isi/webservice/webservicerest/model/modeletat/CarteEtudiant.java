package org.isj.ing3.isi.webservice.webservicerest.model.modeletat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CarteEtudiant implements Serializable {
    private Date annee;
    private String codeUser;
    private Long codeClasse;

}
