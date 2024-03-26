package com.isj.gestiondenote.ClientWeb.Model.ModelPostDataForHigthRequest;

import lombok.Data;

@Data
public class PostNoteForList {
    private String libellefiliere;
    private String libellespecialite;
    private int numero;
    private int anneDebut;
    private String libelleSemestre;
    private String libelleTypeNote;
    private String codeUe;
}
