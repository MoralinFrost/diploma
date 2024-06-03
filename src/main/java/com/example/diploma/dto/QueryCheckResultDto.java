package com.example.diploma.dto;

public record QueryCheckResultDto(
        Integer id,
        QueryCheckDto check,
        QueryTechnologyDto technology,
        Integer mark
) {
}
