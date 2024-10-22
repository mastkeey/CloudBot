package ru.mastkey.cloudservice.support;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
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

    @AfterEach
    public void tearDown() {
        workspaceRepository.deleteAll();
        userRepository.deleteAll();
    }
}
