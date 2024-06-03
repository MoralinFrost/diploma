package com.example.diploma.controller;

import com.example.diploma.dto.GetAllCheckResultResponse;
import com.example.diploma.dto.MutableCheckResultDto;
import com.example.diploma.dto.QueryCheckResultDto;
import com.example.diploma.service.CheckResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/check-results")
public class CheckResultController {
    private final CheckResultService checkResultService;

    @GetMapping
    public ResponseEntity<GetAllCheckResultResponse> getAllCheckResults() {
        return ResponseEntity.ok(new GetAllCheckResultResponse(null));
    }

    @PostMapping
    public ResponseEntity<QueryCheckResultDto> createCheckResult(@Valid @RequestBody MutableCheckResultDto checkResultDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping
    public ResponseEntity<QueryCheckResultDto> updateCheckResult(@Valid @RequestBody MutableCheckResultDto checkResultDto) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckResultById(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

}
