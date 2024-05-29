package com.example.diploma.controller;

import com.example.diploma.dto.GetAllWorkflowResponse;
import com.example.diploma.dto.Workflow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workflows")
public class WorkflowController {

    @GetMapping
    public ResponseEntity<GetAllWorkflowResponse> getAllWorkflows() {
        return ResponseEntity.ok(new GetAllWorkflowResponse(Arrays.stream(Workflow.values()).toList()));
    }

}
