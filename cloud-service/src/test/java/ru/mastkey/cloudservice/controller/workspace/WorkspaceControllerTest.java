package ru.mastkey.cloudservice.controller.workspace;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mastkey.cloudservice.controller.WorkspaceController;
import ru.mastkey.cloudservice.controller.dto.CreateWorkspaceRequest;
import ru.mastkey.cloudservice.controller.dto.WorkspaceResponse;
import ru.mastkey.cloudservice.service.WorkspaceService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WorkspaceController.class)
class WorkspaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkspaceService workspaceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createWorkspace_ShouldReturnWorkspaceResponse() throws Exception {
        var testWorkspaceName = "test-workspace";

        var request = new CreateWorkspaceRequest();
        request.setTelegramUserId(12345L);
        request.setName(testWorkspaceName);

        var workspaceResponse = new WorkspaceResponse();
        workspaceResponse.setName(testWorkspaceName);

        when(workspaceService.createWorkspace(any(CreateWorkspaceRequest.class))).thenReturn(workspaceResponse);

        mockMvc.perform(post("/api/v1/workspaces")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(testWorkspaceName));
    }

    @Test
    void createWorkspace_ShouldReturnBadRequest_WhenRequestIsInvalid() throws Exception {
        var request = new CreateWorkspaceRequest();
        request.setName("test_workspace");

        mockMvc.perform(post("/api/v1/workspaces")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}