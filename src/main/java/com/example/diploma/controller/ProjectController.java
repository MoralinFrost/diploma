package com.example.diploma.controller;

import com.example.diploma.dto.GetAllProjectResponse;
import com.example.diploma.dto.ProjectDto;
import com.example.diploma.security.PrincipalUser;
import com.example.diploma.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<GetAllProjectResponse> getAllProjects() {
        return ResponseEntity.ok(new GetAllProjectResponse(projectService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getProjectDtoById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PROJECT_HEAD')")
    public ResponseEntity<ProjectDto> createProject(
            @Valid @RequestBody ProjectDto projectDto,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(projectDto, principalUser.getId()));
    }

    @PatchMapping("/{projectId}/users/{userId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable Integer projectId, @PathVariable Integer userId) {
        projectService.addUserToProject(projectId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<ProjectDto> updateProject(@Valid @RequestBody ProjectDto projectDto) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.update(projectDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Integer id,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        boolean isDeleted = projectService.deleteById(id, principalUser.getId());
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
