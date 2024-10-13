package ru.mastkey.cloudservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import ru.mastkey.cloudservice.client.S3Client;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.entity.Workspace;
import ru.mastkey.cloudservice.exception.ErrorType;
import ru.mastkey.cloudservice.exception.ServiceException;
import ru.mastkey.cloudservice.repository.UserRepository;
import ru.mastkey.cloudservice.service.impl.UserServiceImpl;
import ru.mastkey.cloudservice.service.impl.WorkspaceServiceImpl;
import ru.mastkey.model.CreateUserRequest;
import ru.mastkey.model.UserResponse;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private ConversionService conversionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WorkspaceServiceImpl workspaceService;

    @Mock
    private S3Client s3Client;

    @InjectMocks
    private UserServiceImpl userService;

    private CreateUserRequest createUserRequest;
    private User user;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        var testTgUserId = 12345L;
        var testChatUserId = 12345L;

        createUserRequest = new CreateUserRequest();
        createUserRequest.setTelegramUserId(testTgUserId);
        createUserRequest.setChatId(testChatUserId);
        createUserRequest.setUsername("testuser");

        user = new User();
        user.setTelegramUserId(testTgUserId);

        userResponse = new UserResponse();
        userResponse.setTelegramUserId(testTgUserId);
        userResponse.setChatId(testChatUserId);
    }

    @Test
    void createUser_ShouldCreateUserSuccessfully() {
        when(userRepository.findByTelegramUserId(createUserRequest.getTelegramUserId()))
                .thenReturn(Optional.empty());
        when(conversionService.convert(createUserRequest, User.class))
                .thenReturn(user);
        when(userRepository.save(user))
                .thenReturn(user);
        when(conversionService.convert(user, UserResponse.class))
                .thenReturn(userResponse);
        var response = userService.createUser(createUserRequest);

        assertThat(response).isNotNull();
        assertThat(createUserRequest.getTelegramUserId()).isEqualTo(response.getTelegramUserId());
        assertThat(createUserRequest.getChatId()).isEqualTo(response.getChatId());
        verify(userRepository).save(user);
        verify(workspaceService)
                .createWorkspace(user.getTelegramUserId(), createUserRequest.getUsername());
    }

    @Test
    void createUser_ShouldThrowConflictException_WhenUserAlreadyExists() {
        when(userRepository.findByTelegramUserId(createUserRequest.getTelegramUserId()))
                .thenReturn(Optional.of(user));

        var exception = assertThrows(ServiceException.class,
            () -> userService.createUser(createUserRequest));

        assertThat(ErrorType.CONFLICT.getCode()).isEqualTo(exception.getCode());
        assertThat("User with id %s already exist".formatted(createUserRequest.getTelegramUserId())).isEqualTo(exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
        verify(workspaceService, never()).createWorkspace(anyLong(), anyString());
    }

    @Test
    void changeCurrentWorkspace_ShouldUpdateCurrentWorkspaceSuccessfully() {
        var telegramUserId = 12345L;
        var newWorkspaceName = "newWorkspace";

        var workspace = new Workspace();
        workspace.setName(newWorkspaceName);

        var user = new User();
        user.setTelegramUserId(telegramUserId);
        user.setWorkspaces(List.of(workspace));

        when(userRepository.findByTelegramUserId(telegramUserId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        userService.changeCurrentWorkspace(telegramUserId, newWorkspaceName);

        assertThat(user.getCurrentWorkspaceName()).isEqualTo(newWorkspaceName);
        verify(userRepository).save(user);
    }

    @Test
    void changeCurrentWorkspace_ShouldThrowNotFoundException_WhenUserDoesNotExist() {
        var telegramUserId = 12345L;
        var newWorkspaceName = "nonExistentWorkspace";

        when(userRepository.findByTelegramUserId(telegramUserId)).thenReturn(Optional.empty());

        var exception = assertThrows(ServiceException.class,
                () -> userService.changeCurrentWorkspace(telegramUserId, newWorkspaceName));

        assertThat(ErrorType.NOT_FOUND.getCode()).isEqualTo(exception.getCode());
        assertThat("User with id %s not found".formatted(telegramUserId)).isEqualTo(exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void changeCurrentWorkspace_ShouldThrowNotFoundException_WhenWorkspaceDoesNotExist() {
        var telegramUserId = 12345L;
        var newWorkspaceName = "nonExistentWorkspace";

        var user = new User();
        user.setTelegramUserId(telegramUserId);
        user.setWorkspaces(List.of());

        when(userRepository.findByTelegramUserId(telegramUserId)).thenReturn(Optional.of(user));

        var exception = assertThrows(ServiceException.class,
                () -> userService.changeCurrentWorkspace(telegramUserId, newWorkspaceName));

        assertThat(ErrorType.NOT_FOUND.getCode()).isEqualTo(exception.getCode());
        assertThat("User with id 12345 does not have a Workspace named nonExistentWorkspace".formatted(telegramUserId, newWorkspaceName)).isEqualTo(exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}