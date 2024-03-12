package com.webservice.microservice.test.oauth.dto;

import com.webservice.microservice.test.oauth.model.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroitDto {
    private String libelle;
    private String description;
    private Integer ecriture;
    private Integer lecture;
    private Integer modification;
    private Integer suppression;
    private ApplicationDto codeapplicationid;

}