package org.isj.ing.gestionuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing.gestionuser.model.Application;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroitDto {
    private Long id;
    @Size(max = 255)
    private String libelle;
    @Size(max = 255)
    private String description;
    @Max(Integer.MAX_VALUE)
    private Integer ecriture;
    @Max(Integer.MAX_VALUE)
    private Integer lecture;
    @Max(Integer.MAX_VALUE)
    private Integer modification;
    @Max(Integer.MAX_VALUE)
    private Integer suppression;
    private ApplicationDto codeapplicationid;

}