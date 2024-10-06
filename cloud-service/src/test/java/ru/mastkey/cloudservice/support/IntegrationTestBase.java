package ru.mastkey.cloudservice.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureObservability
@ContextConfiguration(initializers = {PostgreSQLInitializer.class})
public class IntegrationTestBase {
    @Autowired
    protected WebTestClient webClient;
}
