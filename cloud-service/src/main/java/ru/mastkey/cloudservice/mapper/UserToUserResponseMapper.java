package ru.mastkey.cloudservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.core.convert.converter.Converter;
import ru.mastkey.cloudservice.controller.dto.UserResponse;
import ru.mastkey.cloudservice.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserToUserResponseMapper extends Converter<User, UserResponse> {
    @Override
    UserResponse convert(User source);
}
