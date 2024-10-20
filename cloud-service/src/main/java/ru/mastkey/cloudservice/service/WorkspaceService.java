package ru.mastkey.cloudservice.service;

import ru.mastkey.cloudservice.controller.dto.CreateWorkspaceRequest;
import ru.mastkey.cloudservice.controller.dto.WorkspaceResponse;

public interface WorkspaceService {
    WorkspaceResponse createWorkspace(Long userId, String name);

    WorkspaceResponse createWorkspace(CreateWorkspaceRequest createWorkspaceRequest);
}
