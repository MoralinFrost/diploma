package com.example.diploma.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MutableTimeTrackerDto(
        Integer id,
        @NotNull(message = "userId must be present") Integer userId,
        @NotNull(message = "date must be present") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate date,
        String description
) {
}
