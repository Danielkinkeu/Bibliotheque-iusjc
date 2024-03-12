package org.isj.ing.gestionuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing.gestionuser.model.Role;
import org.isj.ing.gestionuser.model.User;
import org.isj.ing.gestionuser.model.UserRoleId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

    private UserDto codeUserid;
    private List<RoleDto> codeRole;

}