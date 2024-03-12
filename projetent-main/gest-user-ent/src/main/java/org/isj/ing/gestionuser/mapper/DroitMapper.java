package org.isj.ing.gestionuser.mapper;


import org.isj.ing.gestionuser.dto.DroitDto;
import org.isj.ing.gestionuser.model.Droit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DroitMapper extends EntityMapper<DroitDto, Droit> {
}