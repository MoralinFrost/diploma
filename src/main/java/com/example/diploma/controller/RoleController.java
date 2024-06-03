package com.example.diploma.controller;

import com.example.diploma.dto.GetAllRoleResponse;
import com.example.diploma.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GetAllRoleResponse> getAllRoles() {
        return ResponseEntity.ok(new GetAllRoleResponse(roleService.getAllRoles()));
    }

}
