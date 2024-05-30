package com.example.diploma.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MutableTimeTrackerDto(
        Integer id,
        @NotNull(message = "userId must be present") Integer userId,
        @NotNull(message = "date must be present") LocalDate date,
        String description
) {
}
