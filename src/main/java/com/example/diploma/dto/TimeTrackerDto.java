package com.example.diploma.dto;

import java.time.LocalDate;
import java.util.List;

public record TimeTrackerDto(
        Integer id,
        UserDto user,
        LocalDate date,
        String description,
        List<QueryTaskDto> tasks
) {
}
