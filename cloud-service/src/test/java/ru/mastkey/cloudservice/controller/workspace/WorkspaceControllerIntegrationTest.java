package ru.mastkey.cloudservice.controller.workspace;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.mastkey.cloudservice.controller.dto.CreateWorkspaceRequest;
import ru.mastkey.cloudservice.controller.dto.WorkspaceResponse;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.exception.response.ErrorResponse;
import ru.mastkey.cloudservice.support.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class WorkspaceControllerIntegrationTest extends IntegrationTestBase {
    private static final String BASE_URL = "/api/v1/workspaces";

    @Test
    void createWorkspaceSuccessTest() {
        var user = createUser();
        var request = new CreateWorkspaceRequest();
        request.setName("test");
        request.setTelegramUserId(user.getTelegramUserId());

        ResponseEntity<WorkspaceResponse> response =  testRestTemplate
                .postForEntity(BASE_URL, request, WorkspaceResponse.class);

        var workspaceResponse = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        var savedWorkspace = workspaceRepository.findById(workspaceResponse.getWorkspaceId());
        var savedUser = userRepository.findByTelegramUserId(workspaceResponse.getTelegramUserId()).get();

        assertThat(savedWorkspace).isNotEmpty();
        assertThat(savedWorkspace.get().getName()).isEqualTo(workspaceResponse.getName());
        assertThat(savedWorkspace.get().getUser().getId()).isEqualTo(user.getId());
        assertThat(savedUser.getWorkspaces().size()).isEqualTo(1);
    }

    @Test
    void createWorkspaceNotFoundTest() {
        var request = new CreateWorkspaceRequest();
        request.setName("test");
        request.setTelegramUserId(123L);

        ResponseEntity<ErrorResponse> response = testRestTemplate
                .postForEntity(BASE_URL, request, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        var error = response.getBody();

        assertThat(error.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(error.getMessage()).isEqualTo("Пользователь с id 123 не найден.");
    }

    private User createUser() {
        var user = new User();
        user.setTelegramUserId(123L);
        user.setChatId(123L);
        return userRepository.save(user);
    }
}
