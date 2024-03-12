package org.isj.ing3.isi.webservice.webservicerest.model.modeletat;

import lombok.Data;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
public class FicheAbsence {
    private int annee;
    private String dateJury;
    private Long codeClasse;
    private String codeUser;
    private String semestre;
    private String dateDebut;
    private String dateFin;
    private String debutAnneeAcademique;
}
