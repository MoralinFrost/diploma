package com.example.diploma.controller;

import com.example.diploma.dto.GetAllUserResponse;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
        userService.addRole(userId, roleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<Void> removeRoleFromUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
        userService.removeRole(userId, roleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
