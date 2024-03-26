package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie implements Serializable {

    private Long idCategorie;


    private String intituleCategorie;


    private Set<Intervention> interventions;


    private Set<SousIntervention> sousInterventions = new HashSet<>();
}
