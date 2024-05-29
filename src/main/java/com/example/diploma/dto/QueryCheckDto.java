package com.example.diploma.dto;

import java.time.LocalDateTime;

public record QueryCheckDto(
        Integer id,
        UserDto interviewee,
        UserDto interviewer,
        LocalDateTime checkDate,
        Boolean status,
        String feedback
) {
}
