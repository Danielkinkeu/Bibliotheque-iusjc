package org.isj.ing.gestionuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing.gestionuser.model.Droit;
import org.isj.ing.gestionuser.model.Role;
import org.isj.ing.gestionuser.model.RoleDroitId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDroitDto {
    private RoleDto codeRoleid;
    private DroitDto codeDroitid;

}