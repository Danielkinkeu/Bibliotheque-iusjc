package com.isj.gestiondenote.ClientWeb.Model.modeletat;

import lombok.Data;

@Data
public class ReleveNote {
    private String matricules;
    private Long codeClasse;
    private String decision;
    private String typeReleve="Annuel";
    private int anneeDebut;
    private String dateString;
    private Long codeUser;
}
