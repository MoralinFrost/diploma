package com.example.diploma.dto;

import jakarta.validation.constraints.NotBlank;

public record ProjectDto(Integer id, @NotBlank(message = "namespace must be present") String namespace) {
}
