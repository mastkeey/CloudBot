package ru.mastkey.cloudservice.service;

import ru.mastkey.cloudservice.controller.dto.CreateUserRequest;
import ru.mastkey.cloudservice.controller.dto.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
}
