package ru.mastkey.cloudservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mastkey.cloudservice.controller.dto.CreateUserRequest;
import ru.mastkey.cloudservice.controller.dto.UserResponse;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.exception.ErrorType;
import ru.mastkey.cloudservice.exception.ServiceException;
import ru.mastkey.cloudservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ConversionService conversionService;
    private final UserRepository userRepository;
    private final WorkspaceService workspaceService;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        userRepository.findById(request.getId()).ifPresent(user -> {
            throw new ServiceException(ErrorType.CONFLICT,
                    "Пользователь с id %s уже существует.", user.getId());
        });

        var user = conversionService.convert(request, User.class);

        userRepository.save(user);

        workspaceService.createWorkspace(user.getId(), request.getUsername());

        return conversionService.convert(user, UserResponse.class);
    }
}
