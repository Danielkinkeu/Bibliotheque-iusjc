package com.isj.gestiondenote.ClientWeb.Model.modeletat;

import lombok.Data;

import java.io.Serializable;

@Data
public class Pv implements Serializable {
    private String semestre;
    private Long codeClasse;
    private String annee;
    private String codeUser;
    private boolean format;
    private String typeEtat="pdf";
}
