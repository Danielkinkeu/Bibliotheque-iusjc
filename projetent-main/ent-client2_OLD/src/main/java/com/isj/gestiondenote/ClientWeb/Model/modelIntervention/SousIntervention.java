package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SousIntervention implements Serializable {


    private Long idSousIntervention;


    private String intituleSousIntervention;


    private Departement departement;


    private Categorie categorie;

}
