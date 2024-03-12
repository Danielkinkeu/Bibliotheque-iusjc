package com.webservice.microservice.test.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDroitDto {
    private RoleDto coderoleid;
    private DroitDto codeDroitid;

}