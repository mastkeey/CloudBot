package ru.mastkey.cloudservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import ru.mastkey.cloudservice.controller.dto.CreateUserRequest;
import ru.mastkey.cloudservice.controller.dto.UserResponse;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.exception.ErrorType;
import ru.mastkey.cloudservice.exception.ServiceException;
import ru.mastkey.cloudservice.repository.UserRepository;
import ru.mastkey.cloudservice.service.impl.UserServiceImpl;
import ru.mastkey.cloudservice.service.impl.WorkspaceServiceImpl;

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
        assertThat("Пользователь с id 12345 уже существует.").isEqualTo(exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
        verify(workspaceService, never()).createWorkspace(anyLong(), anyString());
    }
}