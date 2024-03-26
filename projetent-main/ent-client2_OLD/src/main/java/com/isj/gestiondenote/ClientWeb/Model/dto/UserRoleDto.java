package com.isj.gestiondenote.ClientWeb.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

    private UserDto codeUserid;
    private List<RoleDto> codeRole;
    private List<DroitDtoPrint> droitDtos;

}