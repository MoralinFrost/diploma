package com.example.diploma.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCommentRequest(
        @NotNull(message = "id must be present") Integer id,
        @NotBlank(message = "comment must be present") String comment
) {
}
