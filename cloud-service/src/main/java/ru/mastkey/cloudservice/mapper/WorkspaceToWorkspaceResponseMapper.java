package ru.mastkey.cloudservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import ru.mastkey.cloudservice.configuration.MapperConfiguration;
import ru.mastkey.cloudservice.controller.dto.WorkspaceResponse;
import ru.mastkey.cloudservice.entity.Workspace;

@Mapper(config = MapperConfiguration.class)
public interface WorkspaceToWorkspaceResponseMapper extends Converter<Workspace, WorkspaceResponse> {
    @Override
    @Mapping(target = "userId", source = "user.id")
    WorkspaceResponse convert(Workspace source);
}
