package com.isj.gestiondenote.ClientWeb.Model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroitDto {
    private Long id;

    private String libelle;

    private String description;

    private Integer ecriture;

    private Integer lecture;

    private Integer modification;

    private Integer suppression;
        private ApplicationDto codeapplicationid;

}