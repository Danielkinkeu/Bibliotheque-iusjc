package com.webservice.microservice.test.oauth.mapper;

import com.webservice.microservice.test.oauth.dto.RoleDto;
import com.webservice.microservice.test.oauth.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role>{
}
