package org.isj.ing.gestionuser.mapper;

import org.isj.ing.gestionuser.dto.RoleDto;
import org.isj.ing.gestionuser.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
}