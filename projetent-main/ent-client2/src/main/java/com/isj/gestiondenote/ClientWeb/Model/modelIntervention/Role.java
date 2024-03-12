package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private Long idRole;

    private String name;


}
