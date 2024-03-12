package org.isj.ing.gestionuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto{
    private Long id;
    @Size(max = 255)
    private String libelle;
    @Size(max = 255)
    private String description;

}