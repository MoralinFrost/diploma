package com.example.diploma.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(
        @NotBlank(message = "comment must be present") String comment,
        @NotNull(message = "taskId must be present") Integer taskId
) {
}
