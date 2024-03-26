package com.isj.gestiondenote.ClientWeb.Model.modeletat;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class CarteEtudiant implements Serializable {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date annee;
    private String codeUser;
    private Long codeClasse;

}
