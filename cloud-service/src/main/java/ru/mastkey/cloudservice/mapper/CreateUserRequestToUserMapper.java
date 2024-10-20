package ru.mastkey.cloudservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.core.convert.converter.Converter;
import ru.mastkey.cloudservice.controller.dto.CreateUserRequest;
import ru.mastkey.cloudservice.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateUserRequestToUserMapper extends Converter<CreateUserRequest, User> {

    @Override
    User convert(CreateUserRequest source);
}
