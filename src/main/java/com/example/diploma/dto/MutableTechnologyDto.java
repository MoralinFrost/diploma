package com.example.diploma.dto;

import jakarta.validation.constraints.NotBlank;

public record MutableTechnologyDto(
        Integer id,
        @NotBlank(message = "techType must be present") String techType,
        @NotBlank(message = "techName must be present") String techName
) {
}
