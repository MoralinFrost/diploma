package com.example.diploma.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MutableCheckDto(
        Integer id,
        @NotNull(message = "intervieweeId must be present") Integer intervieweeId,
        @NotNull(message = "interviewerId must be present") Integer interviewerId,
        @NotNull(message = "checkDate must be present") LocalDateTime checkDate,
        Boolean status,
        String feedback
) {
}
