package com.webservice.microservice.test.oauth.mapper;

import com.webservice.microservice.test.oauth.dto.*;
import com.webservice.microservice.test.oauth.model.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}