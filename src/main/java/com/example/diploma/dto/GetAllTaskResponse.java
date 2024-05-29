package com.example.diploma.dto;

import java.util.List;

public record GetAllTaskResponse(List<QueryTaskDto> tasks) {
}
