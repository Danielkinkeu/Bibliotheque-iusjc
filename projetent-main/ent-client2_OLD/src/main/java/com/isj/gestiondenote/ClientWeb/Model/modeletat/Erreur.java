package com.isj.gestiondenote.ClientWeb.Model.modeletat;

import lombok.Data;

@Data
public class Erreur {
    private int code;
    private String message;
    private String description;
}
