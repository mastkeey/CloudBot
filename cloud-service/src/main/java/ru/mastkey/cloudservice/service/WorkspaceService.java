package ru.mastkey.cloudservice.service;

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

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final S3Client s3Client;
    private final ConversionService conversionService;

    public WorkspaceResponse createWorkspace(CreateWorkspaceRequest createWorkspaceRequest) {
        return createWorkspace(createWorkspaceRequest.getUserId(), createWorkspaceRequest.getName());
    }

    public WorkspaceResponse createWorkspace(Long userId, String name) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new ServiceException(ErrorType.NOT_FOUND,
                        "Пользователь с id %s не найден.", userId)
        );

        var workspace = new Workspace();
        workspace.setName(name);
        workspace.setUser(user);
        workspaceRepository.save(workspace);
        s3Client.createBucketIfNotExists(workspace.getId().toString());

        return conversionService.convert(workspace, WorkspaceResponse.class);
    }
}
