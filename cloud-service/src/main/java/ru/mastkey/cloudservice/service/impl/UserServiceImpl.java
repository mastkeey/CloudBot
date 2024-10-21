package ru.mastkey.cloudservice.service.impl;

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
import ru.mastkey.cloudservice.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ConversionService conversionService;
    private final UserRepository userRepository;
    private final WorkspaceServiceImpl workspaceService;

    @Transactional
    @Override
    public UserResponse createUser(CreateUserRequest request) {
        userRepository.findByTelegramUserId(request.getTelegramUserId()).ifPresent(user -> {
            throw new ServiceException(ErrorType.CONFLICT,
                    "Пользователь с id %s уже существует.", user.getTelegramUserId());
        });

        var user = conversionService.convert(request, User.class);

        var savedUser = userRepository.save(user);

        workspaceService.createWorkspace(savedUser.getTelegramUserId(), request.getUsername());

        return conversionService.convert(savedUser, UserResponse.class);
    }
}
