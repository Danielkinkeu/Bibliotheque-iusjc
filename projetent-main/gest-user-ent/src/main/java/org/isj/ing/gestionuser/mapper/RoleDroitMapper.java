package org.isj.ing.gestionuser.mapper;

import org.isj.ing.gestionuser.dto.RoleDroitDto;
import org.isj.ing.gestionuser.model.RoleDroit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleDroitMapper extends EntityMapper<RoleDroitDto, RoleDroit> {
}