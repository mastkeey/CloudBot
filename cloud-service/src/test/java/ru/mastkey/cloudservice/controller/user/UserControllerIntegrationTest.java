package ru.mastkey.cloudservice.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.mastkey.cloudservice.controller.dto.CreateUserRequest;
import ru.mastkey.cloudservice.controller.dto.UserResponse;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.exception.response.ErrorResponse;
import ru.mastkey.cloudservice.support.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerIntegrationTest extends IntegrationTestBase {

    private static final String BASE_URL = "/api/v1/users";

    @Test
    void createUserSuccessTest() {
        var request = createCreateUserRequest();

        ResponseEntity<UserResponse> response = testRestTemplate
                .postForEntity(BASE_URL, request, UserResponse.class);

        var user = userRepository.findAll().get(0);
        var body = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(user.getTelegramUserId()).isEqualTo(request.getTelegramUserId());
        assertThat(user.getChatId()).isEqualTo(request.getChatId());
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getWorkspaces().get(0).getName()).isEqualTo(request.getUsername());
        assertThat(body.getChatId()).isEqualTo(request.getChatId());
        assertThat(body.getTelegramUserId()).isEqualTo(request.getTelegramUserId());
        assertThat(body.getId()).isNotNull();
    }

    @Test
    void createUserConflictTest() {
        var request = createCreateUserRequest();
        var user = User.builder()
                .telegramUserId(request.getTelegramUserId())
                .chatId(request.getChatId())
                .build();

        userRepository.save(user);

        ResponseEntity<ErrorResponse> response = testRestTemplate
                .postForEntity(BASE_URL, request, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody().getMessage()).contains(String.format("Пользователь с id %s уже существует.", request.getTelegramUserId()));
    }

    public CreateUserRequest createCreateUserRequest() {
        var request = new CreateUserRequest();
        request.setTelegramUserId(123L);
        request.setChatId(321L);
        request.setUsername("mastkey");
        return request;
    }
}
