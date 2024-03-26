package com.isj.gestiondenote.ClientWeb.Model.modeletat;

import lombok.Data;

@Data
public class PVSynthese {
    private String matricules;
    private Long codeClasse;
    private int anneeDebut;
    private String dateString;
    private float creditsMinAdmission;
    private float mgpMinAdmission;
    private int nbAnneesCursus;
    boolean imprimerAttestations;
    String dateJury;
}
