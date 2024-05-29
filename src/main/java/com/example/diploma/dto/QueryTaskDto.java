package com.example.diploma.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record QueryTaskDto(
        Integer id,
        String title,
        String description,
        Priority priority,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime deadlineDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime closedDate,
        UserDto assignedUser,
        ProjectDto project,
        Workflow workflow
) {
}
