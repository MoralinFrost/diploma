package com.example.diploma.dto;

import java.time.LocalDateTime;

public record QueryTaskDto(
        Integer id,
        String title,
        String description,
        Priority priority,
        LocalDateTime deadlineDate,
        LocalDateTime createdDate,
        LocalDateTime closedDate,
        UserDto assignedUser,
        ProjectDto project,
        Workflow workflow
) {
}
