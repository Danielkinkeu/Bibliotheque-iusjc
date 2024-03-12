package org.isj.ing.gestionuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing.gestionuser.annotation.CheckEmail;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @Size(max = 255)
    @NotBlank
    private String name;
    @CheckEmail
    @Size(max = 255)
    @NotBlank
    private String email;
    @Size(max = 255)
    @NotBlank
    private String password;
    private String matricule;
    private String filiere;
    private String classe;


}