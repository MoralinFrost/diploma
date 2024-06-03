package com.example.diploma.controller;

import com.example.diploma.dto.GetAllCheckResponse;
import com.example.diploma.dto.MutableCheckDto;
import com.example.diploma.dto.QueryCheckDto;
import com.example.diploma.service.CheckService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CheckController {
    private final CheckService checkService;

    @GetMapping
    public ResponseEntity<GetAllCheckResponse> getAllChecks(
            @RequestParam(value = "intervieweeId", required = false) Integer intervieweeId,
            @RequestParam(value = "interviewerId", required = false) Integer interviewerId
    ) {
        return ResponseEntity.ok(new GetAllCheckResponse(checkService.getAllChecks(intervieweeId, interviewerId)));
    }

    @PostMapping
    public ResponseEntity<QueryCheckDto> createCheck(@Valid @RequestBody MutableCheckDto checkDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(checkService.createCheck(checkDto));
    }

    @PutMapping
    public ResponseEntity<QueryCheckDto> updateCheck(@Valid @RequestBody MutableCheckDto checkDto) {
        return ResponseEntity.ok(checkService.updateCheck(checkDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheck(@PathVariable Integer id) {
        checkService.deleteCheck(id);
        return ResponseEntity.noContent().build();
    }

}
