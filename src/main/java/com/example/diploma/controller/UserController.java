package com.example.diploma.controller;

import com.example.diploma.dto.GetAllUserResponse;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<GetAllUserResponse> getUserById() {
        return ResponseEntity.ok(new GetAllUserResponse(userService.findAll()));
    }
}
