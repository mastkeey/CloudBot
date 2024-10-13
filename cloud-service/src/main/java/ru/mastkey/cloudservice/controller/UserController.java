package ru.mastkey.cloudservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.mastkey.api.UserControllerApi;
import ru.mastkey.cloudservice.service.UserService;
import ru.mastkey.model.CreateUserRequest;
import ru.mastkey.model.UserResponse;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerApi {
    private final UserService userService;

    @Override
    public ResponseEntity<Void> changeCurrentWorkspace(String newWorkspaceName, Long telegramUserId) {
        userService.changeCurrentWorkspace(telegramUserId, newWorkspaceName);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserResponse> createUser(CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }
}
