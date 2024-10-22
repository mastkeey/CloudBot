package ru.mastkey.cloudservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import ru.mastkey.cloudservice.client.S3Client;
import ru.mastkey.cloudservice.controller.dto.CreateWorkspaceRequest;
import ru.mastkey.cloudservice.controller.dto.WorkspaceResponse;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.entity.Workspace;
import ru.mastkey.cloudservice.exception.ErrorType;
import ru.mastkey.cloudservice.exception.ServiceException;
import ru.mastkey.cloudservice.repository.UserRepository;
import ru.mastkey.cloudservice.repository.WorkspaceRepository;
import ru.mastkey.cloudservice.service.impl.WorkspaceServiceImpl;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkspaceServiceImplTest {

    @Mock
    private WorkspaceRepository workspaceRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private S3Client s3Client;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private WorkspaceServiceImpl workspaceService;

    private CreateWorkspaceRequest createWorkspaceRequest;
    private User user;
    private Workspace workspace;
    private WorkspaceResponse workspaceResponse;

    @BeforeEach
    void setUp() {
        var testTgUserId = 12345L;
        var testWorkspaceName = "test_workspace";

        createWorkspaceRequest = new CreateWorkspaceRequest();
        createWorkspaceRequest.setTelegramUserId(testTgUserId);
        createWorkspaceRequest.setName(testWorkspaceName);

        user = new User();
        user.setTelegramUserId(testTgUserId);

        workspace = new Workspace();
        workspace.setName(testWorkspaceName);
        workspace.setUser(user);
        workspace.setId(UUID.randomUUID());

        workspaceResponse = new WorkspaceResponse();
        workspaceResponse.setName(testWorkspaceName);
    }

    @Test
    void createWorkspace_ShouldCreateWorkspaceSuccessfully() {
        when(userRepository.findByTelegramUserId(createWorkspaceRequest.getTelegramUserId()))
                .thenReturn(Optional.of(user));
        when(workspaceRepository.save(any(Workspace.class)))
                .thenReturn(workspace);
        when(conversionService.convert(workspace, WorkspaceResponse.class))
                .thenReturn(workspaceResponse);

        WorkspaceResponse response = workspaceService.createWorkspace(createWorkspaceRequest);

        assertThat(response).isNotNull();
        assertThat(createWorkspaceRequest.getName()).isEqualTo(response.getName());
        verify(workspaceRepository).save(any(Workspace.class));
        verify(s3Client).createBucketIfNotExists(anyString());
    }

    @Test
    void createWorkspace_ShouldThrowNotFoundException_WhenUserNotFound() {
        when(userRepository.findByTelegramUserId(createWorkspaceRequest.getTelegramUserId()))
                .thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class,
                () -> workspaceService.createWorkspace(createWorkspaceRequest));

        assertThat(ErrorType.NOT_FOUND.getCode()).isEqualTo(exception.getCode());
        assertThat("Пользователь с id 12345 не найден.").isEqualTo(exception.getMessage());
        verify(workspaceRepository, never()).save(any(Workspace.class));
        verify(s3Client, never()).createBucketIfNotExists(anyString());
    }

    @Test
    void createWorkspace_WithUserIdAndName_ShouldCreateWorkspaceSuccessfully() {
        when(userRepository.findByTelegramUserId(12345L))
                .thenReturn(Optional.of(user));
        when(workspaceRepository.save(any(Workspace.class)))
                .thenReturn(workspace);
        when(conversionService.convert(workspace, WorkspaceResponse.class))
                .thenReturn(workspaceResponse);

        WorkspaceResponse response = workspaceService.createWorkspace(12345L, "test_workspace");

        assertThat(response).isNotNull();
        assertThat("test_workspace").isEqualTo(response.getName());
        verify(workspaceRepository).save(any(Workspace.class));
        verify(s3Client).createBucketIfNotExists(anyString());
    }
}