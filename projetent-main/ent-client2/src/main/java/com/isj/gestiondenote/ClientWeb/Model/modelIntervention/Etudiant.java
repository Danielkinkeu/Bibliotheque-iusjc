package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {


    private long code;

    private String matricule;

    private String emailEtudiant;

    private String nomEtudiant;


    private String prenomEtudiant;


    private String codeAuthentification;





    private String login;


    private Set<Intervention> interventions = new HashSet<>();



}
