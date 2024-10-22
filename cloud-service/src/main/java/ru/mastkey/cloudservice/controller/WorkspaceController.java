package ru.mastkey.cloudservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mastkey.cloudservice.controller.dto.CreateWorkspaceRequest;
import ru.mastkey.cloudservice.controller.dto.WorkspaceResponse;
import ru.mastkey.cloudservice.service.WorkspaceService;

@RestController
@RequestMapping("/api/v1/workspaces")
@RequiredArgsConstructor
@Validated
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<WorkspaceResponse> createWorkspace(@RequestBody @Valid CreateWorkspaceRequest request) {
        return ResponseEntity.ok(workspaceService.createWorkspace(request));
    }
}
