package com.example.diploma.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @NotBlank(message = "firstname must be present") String firstname,
        @NotBlank(message = "lastname must be present") String lastname,
        @Email @NotBlank(message = "email must be present") String email,
        @NotBlank(message = "password must be present") String password
) {
}
