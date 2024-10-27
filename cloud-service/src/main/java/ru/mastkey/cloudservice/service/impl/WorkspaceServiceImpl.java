package ru.mastkey.cloudservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.mastkey.cloudservice.client.S3Client;
import ru.mastkey.cloudservice.controller.dto.CreateWorkspaceRequest;
import ru.mastkey.cloudservice.controller.dto.WorkspaceResponse;
import ru.mastkey.cloudservice.entity.Workspace;
import ru.mastkey.cloudservice.exception.ErrorType;
import ru.mastkey.cloudservice.exception.ServiceException;
import ru.mastkey.cloudservice.repository.UserRepository;
import ru.mastkey.cloudservice.repository.WorkspaceRepository;
import ru.mastkey.cloudservice.service.WorkspaceService;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final S3Client s3Client;
    private final ConversionService conversionService;

    @Override
    public WorkspaceResponse createWorkspace(CreateWorkspaceRequest createWorkspaceRequest) {
        return createWorkspace(createWorkspaceRequest.getTelegramUserId(), createWorkspaceRequest.getName());
    }

    @Override
    public WorkspaceResponse createWorkspace(Long telegramUserId, String name) {
        var user = userRepository.findByTelegramUserId(telegramUserId).orElseThrow(
                () -> new ServiceException(ErrorType.NOT_FOUND,
                        "Пользователь с id %s не найден.", telegramUserId)
        );

        var workspace = new Workspace();
        workspace.setName(name);
        workspace.setUser(user);

        var savedWorkspace = workspaceRepository.save(workspace);
        s3Client.createBucketIfNotExists(savedWorkspace.getId().toString());

        return conversionService.convert(savedWorkspace, WorkspaceResponse.class);
    }
}
