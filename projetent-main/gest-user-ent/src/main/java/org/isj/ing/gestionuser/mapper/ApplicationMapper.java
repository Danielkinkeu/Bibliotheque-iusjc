package org.isj.ing.gestionuser.mapper;


import org.isj.ing.gestionuser.dto.ApplicationDto;
import org.isj.ing.gestionuser.model.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper extends EntityMapper<ApplicationDto, Application> {
}