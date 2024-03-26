package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personnel{


    private long idPersonnel;


    private String nomPersonnel;


    private String prenomPersonnel;


    private String emailPersonnel;




    private String login;


    private Set<Intervention> interventions = new HashSet<>();


    private Departement departement;


}
