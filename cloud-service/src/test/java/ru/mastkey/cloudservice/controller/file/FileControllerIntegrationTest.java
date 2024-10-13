package ru.mastkey.cloudservice.controller.file;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.mastkey.cloudservice.support.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FileControllerIntegrationTest extends IntegrationTestBase {

    @Test
    void uploadFileSuccessTest() {
        var savedWorkspace = createWorkspaceWithUser();

        ClassPathResource resource1 = new ClassPathResource("files/testfile.txt");
        ClassPathResource bigResource = new ClassPathResource("files/large_file.txt");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", resource1);
        body.add("files", bigResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = testRestTemplate.postForEntity(
                "/api/v1/files/users/" + savedWorkspace.getUser().getTelegramUserId(),
                requestEntity,
                Void.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        var workspace = workspaceRepository.findById(savedWorkspace.getId()).get();
        assertThat(workspace.getFiles().size()).isEqualTo(2);
    }

    @Test
    void uploadFilesWithSameFilenamesSuccessTest() {
        var savedWorkspace = createWorkspaceWithUser();

        ClassPathResource resource1 = new ClassPathResource("files/testfile.txt");
        ClassPathResource resource2 = new ClassPathResource("files/testfile.csv");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", resource1);
        body.add("files", resource2);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = testRestTemplate.postForEntity(
                "/api/v1/files/users/" + savedWorkspace.getUser().getTelegramUserId(),
                requestEntity,
                Void.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        var workspace = workspaceRepository.findById(savedWorkspace.getId()).get();
        assertThat(workspace.getFiles().size()).isEqualTo(2);
    }

    @Test
    void uploadFileToNonExistingUserTest() {
        var nonExistingUserId = 14212L;

        ClassPathResource resource = new ClassPathResource("files/testfile.txt");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", resource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = testRestTemplate.postForEntity(
                "/api/v1/files/users/" + nonExistingUserId,
                requestEntity,
                Void.class
        );

        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
    }

}