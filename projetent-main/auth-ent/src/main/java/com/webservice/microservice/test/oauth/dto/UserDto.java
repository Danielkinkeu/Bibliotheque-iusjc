package com.webservice.microservice.test.oauth.dto;

import com.webservice.microservice.test.oauth.annotation.CheckEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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