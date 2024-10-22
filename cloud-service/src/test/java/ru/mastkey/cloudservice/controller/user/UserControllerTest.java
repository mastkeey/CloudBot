package ru.mastkey.cloudservice.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mastkey.cloudservice.controller.UserController;
import ru.mastkey.cloudservice.controller.dto.CreateUserRequest;
import ru.mastkey.cloudservice.controller.dto.UserResponse;
import ru.mastkey.cloudservice.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser_ShouldReturnUserResponse() throws Exception {
        var testTgUserId = 12345L;
        var testChatUserId = 12345L;

        var request = new CreateUserRequest();
        request.setTelegramUserId(testTgUserId);
        request.setUsername("testuser");
        request.setChatId(testChatUserId);

        var userResponse = new UserResponse();
        userResponse.setTelegramUserId(testTgUserId);
        userResponse.setChatId(testChatUserId);

        when(userService.createUser(any(CreateUserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.telegramUserId").value(testTgUserId))
                .andExpect(jsonPath("$.chatId").value(testChatUserId));
    }

    @Test
    void createUser_ShouldReturnBadRequest_WhenRequestIsInvalid() throws Exception {
        var request = new CreateUserRequest();
        request.setUsername("testuser");
        request.setTelegramUserId(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}