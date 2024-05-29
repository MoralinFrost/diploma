package com.example.diploma.controller;

import com.example.diploma.dto.GetAllTechnologyTypeResponse;
import com.example.diploma.dto.TechnologyType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/technology-types")
public class TechnologyTypeController {

    @GetMapping
    public ResponseEntity<GetAllTechnologyTypeResponse> getAllWorkflows() {
        return ResponseEntity.ok(new GetAllTechnologyTypeResponse(Arrays.stream(TechnologyType.values()).toList()));
    }

}
