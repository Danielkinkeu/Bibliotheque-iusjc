package org.isj.ing.gestionuser.mapper;

import org.isj.ing.gestionuser.dto.UserDto;
import org.isj.ing.gestionuser.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}