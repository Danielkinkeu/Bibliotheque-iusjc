package com.isj.gestiondenote.ClientWeb.Model.dto;

import lombok.Data;

@Data
public class DroitDtoPrint {
    private  String libelle;
    private String description;
    private Integer ecriture;
    private Integer lecture;
    private Integer modification;
    private Integer suppression;

}
