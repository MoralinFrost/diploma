package com.example.diploma.controller;

import com.example.diploma.dto.GetAllTechnologyResponse;
import com.example.diploma.dto.MutableTechnologyDto;
import com.example.diploma.dto.QueryTechnologyDto;
import com.example.diploma.service.TechnologyService;
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
@RequestMapping("/technologies")
public class TechnologyController {
    private final TechnologyService technologyService;

    @GetMapping
    public ResponseEntity<GetAllTechnologyResponse> getAllTechnologies() {
        return ResponseEntity.ok(new GetAllTechnologyResponse(technologyService.getAllTechnologies()));
    }

    @PostMapping
    public ResponseEntity<QueryTechnologyDto> createTechnology(@Valid @RequestBody MutableTechnologyDto technologyDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(technologyService.createTechnology(technologyDto));
    }

    @PutMapping
    public ResponseEntity<QueryTechnologyDto> updateTechnology(@Valid @RequestBody MutableTechnologyDto technologyDto) {
        return ResponseEntity.ok(technologyService.updateTechnology(technologyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Integer id) {
        technologyService.deleteTechnology(id);
        return ResponseEntity.noContent().build();
    }

}
