package com.isj.gestiondenote.ClientWeb.Model.modeletat;

import lombok.Data;

import java.io.Serializable;

@Data
public class FicheAbsence implements Serializable {
    private int annee;
    private String dateJury;
    private Long codeClasse;
    private String codeUser;
    private String semestre;
    private String dateDebut;
    private String dateFin;
    private String debutAnneeAcademique;
}
