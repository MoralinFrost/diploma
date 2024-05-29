package com.example.diploma.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MutableTaskDto(
        Integer id,
        @NotBlank(message = "title must be present") String title,
        String description,
        Priority priority,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime deadlineDate,
        @NotNull(message = "projectId must be present") Integer projectId,
        Workflow workflow
) {
    public MutableTaskDto {
        if (workflow == null) {
            workflow = Workflow.NEW;
        }
        if (priority == null) {
            priority = Priority.LOW;
        }
    }
}
