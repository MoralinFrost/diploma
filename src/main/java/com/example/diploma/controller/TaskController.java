package com.example.diploma.controller;

import com.example.diploma.dto.GetAllTaskResponse;
import com.example.diploma.dto.MutableTaskDto;
import com.example.diploma.dto.QueryTaskDto;
import com.example.diploma.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<GetAllTaskResponse> getAllTasks(
            @RequestParam("namespaceId") Integer namespaceId,
            @RequestParam(value = "user_id", required = false) Integer userId) {
        if (userId != null) {
            return ResponseEntity.ok(new GetAllTaskResponse(taskService.getAllByUserId(namespaceId, userId)));
        }
        return ResponseEntity.ok(new GetAllTaskResponse(taskService.getAll(namespaceId)));
    }

    @PostMapping
    public ResponseEntity<QueryTaskDto> createTask(@Valid @RequestBody MutableTaskDto mutableTask) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(mutableTask));
    }

    @PutMapping
    public ResponseEntity<QueryTaskDto> updateTask(@Valid @RequestBody QueryTaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(taskDto));
    }

    @PatchMapping("/{taskId}/users/{userId}")
    public ResponseEntity<Void> assignUserToTask(@PathVariable Integer taskId, @PathVariable Integer userId) {
        taskService.assignUserToTask(taskId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{taskId}/users/{userId}")
    public ResponseEntity<Void> unassignUserToTask(@PathVariable Integer taskId, @PathVariable Integer userId) {
        taskService.unassignUserFromTask(taskId, userId);
        return ResponseEntity.ok().build();
    }

}
