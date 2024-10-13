package ru.mastkey.cloudservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import ru.mastkey.cloudservice.controller.dto.CreateUserRequest;
import ru.mastkey.cloudservice.controller.dto.UserResponse;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.exception.response.ErrorResponse;
import ru.mastkey.cloudservice.support.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest extends IntegrationTestBase {

    private static final String BASE_URL = "/api/users";

    @Test
    void createUserSuccessTest() {
        var request = createCreateUserRequest();

        var response = webClient.post()
                .uri(BASE_URL)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponse.class)
                .returnResult()
                .getResponseBody();

        var user = userRepository.findAll().get(0);

        assertThat(user.getId()).isEqualTo(request.getId());
        assertThat(user.getChatId()).isEqualTo(request.getChatId());
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getWorkspaces().get(0).getName()).isEqualTo(request.getUsername());
        assertThat(response.getChatId()).isEqualTo(request.getChatId());
        assertThat(response.getId()).isEqualTo(request.getId());
    }

    @Test
    void createUserConflictTest() {
        var request = createCreateUserRequest();
        var user = User.builder()
                .id(request.getId())
                .chatId(request.getChatId())
                .build();

        userRepository.save(user);

        var error = webClient.post()
                .uri(BASE_URL)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.CONFLICT)
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(error.getMessage()).contains(String.format("Пользователь с id %s уже существует.", request.getId()));
    }

    public CreateUserRequest createCreateUserRequest() {
        var request = new CreateUserRequest();
        request.setId(123L);
        request.setChatId(321L);
        request.setUsername("mastkey");
        return request;
    }
}
