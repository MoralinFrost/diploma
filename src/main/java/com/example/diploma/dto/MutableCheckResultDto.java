package com.example.diploma.dto;

import jakarta.validation.constraints.NotNull;

public record MutableCheckResultDto(
        Integer id,
        @NotNull(message = "checkId must be present") Integer checkId,
        @NotNull(message = "technologyId must be present") Integer technologyId,
        @NotNull(message = "mark must be present") Integer mark
) {
}
