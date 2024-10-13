package ru.mastkey.cloudservice.support;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.mastkey.cloudservice.client.S3Client;
import ru.mastkey.cloudservice.entity.User;
import ru.mastkey.cloudservice.entity.Workspace;
import ru.mastkey.cloudservice.repository.FileRepository;
import ru.mastkey.cloudservice.repository.UserRepository;
import ru.mastkey.cloudservice.repository.WorkspaceRepository;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {PostgreSQLInitializer.class, MinioInitializer.class})
@TestPropertySource(locations = "classpath:application-test.yml")
public class IntegrationTestBase {
    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected WorkspaceRepository workspaceRepository;

    @Autowired
    protected FileRepository fileRepository;

    @Autowired
    protected S3Client s3Client;

    protected User createUser() {
        var user = new User();
        user.setTelegramUserId(123L);
        user.setChatId(123L);
        user.setBucketName("test");
        s3Client.createBucketIfNotExists(user.getBucketName());
        return userRepository.save(user);
    }

    protected Workspace createWorkspaceWithUser() {
        var user = userRepository.save(User.builder()
                .telegramUserId(1L)
                .chatId(1L)
                .bucketName("mastkey-1")
                .currentWorkspaceName("mastkey")
                .build());

        var workspace = Workspace.builder()
                .name("mastkey")
                .user(user)
                .build();

        s3Client.createBucketIfNotExists(user.getBucketName());
        return workspaceRepository.save(workspace);
    }

    @AfterEach
    public void tearDown() {
        workspaceRepository.deleteAll();
        userRepository.deleteAll();
        fileRepository.deleteAll();
    }
}
