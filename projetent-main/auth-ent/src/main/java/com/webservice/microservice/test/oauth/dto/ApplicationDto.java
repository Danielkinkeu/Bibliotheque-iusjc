package com.webservice.microservice.test.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
    private Long id;
    @Size(max = 255)
    private String libelle;

}