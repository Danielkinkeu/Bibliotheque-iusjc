package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long idUser;



    private String nom;


    private String prenom;


    private String email;

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }


    private String password;

    private String subject;


    private String login;


   private Role role;


}
