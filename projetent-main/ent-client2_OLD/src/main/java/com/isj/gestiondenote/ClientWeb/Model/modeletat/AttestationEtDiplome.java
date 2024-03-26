package com.isj.gestiondenote.ClientWeb.Model.modeletat;

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
