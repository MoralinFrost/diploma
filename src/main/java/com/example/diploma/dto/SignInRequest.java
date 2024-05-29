package com.example.diploma.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @Email @NotBlank(message = "email must be present") String email,
        @NotBlank(message = "password must be present") String password
) {
}
