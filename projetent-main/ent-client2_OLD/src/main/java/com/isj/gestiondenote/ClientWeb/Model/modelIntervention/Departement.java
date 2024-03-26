package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departement implements Serializable {


    private Long idDepartement;


    private String intituleDepartement;


    private Set<Intervention> interventions;


    private Set<Personnel> personnels = new HashSet<>();


    private Set<SousIntervention> sousInterventions = new HashSet<>();





}
