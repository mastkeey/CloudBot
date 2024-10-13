package ru.mastkey.cloudservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import ru.mastkey.cloudservice.controller.dto.CreateWorkspaceRequest;
import ru.mastkey.cloudservice.controller.dto.WorkspaceResponse;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.exception.response.ErrorResponse;
import ru.mastkey.cloudservice.support.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class WorkspaceControllerTest extends IntegrationTestBase {
    private static final String BASE_URL = "/api/workspaces";

    @Test
    void createWorkspaceSuccessTest() {
        var user = createUser();
        var request = new CreateWorkspaceRequest();
        request.setName("test");
        request.setUserId(user.getId());

        var response = webClient.post().uri(BASE_URL)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(WorkspaceResponse.class)
                .returnResult()
                .getResponseBody();

        var savedWorkspace = workspaceRepository.findById(response.getId());
        var savedUser = userRepository.findById(response.getUserId()).get();

        assertThat(savedWorkspace).isNotEmpty();
        assertThat(savedWorkspace.get().getName()).isEqualTo(response.getName());
        assertThat(savedWorkspace.get().getUser().getId()).isEqualTo(user.getId());
        assertThat(savedUser.getWorkspaces().size()).isEqualTo(1);
    }

    @Test
    void createWorkspaceNotFoundTest() {
        var request = new CreateWorkspaceRequest();
        request.setName("test");
        request.setUserId(123L);

        var error = webClient.post().uri(BASE_URL)
                .bodyValue(request)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(error.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(error.getMessage()).isEqualTo("Пользователь с id 123 не найден.");
    }

    private User createUser() {
        var user = new User();
        user.setId(123L);
        user.setChatId(123L);
        return userRepository.save(user);
    }
}
