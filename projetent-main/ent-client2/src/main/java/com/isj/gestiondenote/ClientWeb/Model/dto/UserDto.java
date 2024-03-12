package com.isj.gestiondenote.ClientWeb.Model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String matricule;
    private String filiere;
    private String classe;


}