package com.example.diploma.controller;

import com.example.diploma.dto.GetAllPriorityResponse;
import com.example.diploma.dto.Priority;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/priorities")
public class PriorityController {

    @GetMapping
    public ResponseEntity<GetAllPriorityResponse> getAllPriorities() {
        return ResponseEntity.ok(new GetAllPriorityResponse(Arrays.stream(Priority.values()).toList()));
    }


}
