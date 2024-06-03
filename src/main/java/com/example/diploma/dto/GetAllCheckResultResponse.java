package com.example.diploma.dto;

import com.example.diploma.entity.CheckResult;

import java.util.List;

public record GetAllCheckResultResponse(List<CheckResult> checkResults) {
}
