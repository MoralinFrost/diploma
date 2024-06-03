package com.example.diploma.controller;

import com.example.diploma.dto.GetAllTimeTrackerResponse;
import com.example.diploma.dto.MutableTimeTrackerDto;
import com.example.diploma.dto.TimeTrackerDto;
import com.example.diploma.service.TimeTrackerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time-trackers")
public class TimeTrackerController {
    private final TimeTrackerService timeTrackerService;

    @GetMapping
    public ResponseEntity<GetAllTimeTrackerResponse> getAllTimeTracker(@RequestParam Integer userId) {
        return ResponseEntity.ok(new GetAllTimeTrackerResponse(timeTrackerService.getAllTimeTrackers(userId)));
    }

    @PostMapping
    public ResponseEntity<TimeTrackerDto> createTimeTracker(@Valid @RequestBody MutableTimeTrackerDto timeTracker) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeTrackerService.createTimeTracker(timeTracker));
    }

    @PutMapping
    public ResponseEntity<TimeTrackerDto> updateTimeTracker(@Valid @RequestBody MutableTimeTrackerDto timeTracker) {
        return ResponseEntity.status(HttpStatus.OK).body(timeTrackerService.updateTimeTracker(timeTracker));
    }

    @PatchMapping("/{timeTrackerId}/tasks/{taskId}")
    public ResponseEntity<Void> addTaskToTimeTracker(@PathVariable Integer timeTrackerId, @PathVariable Integer taskId) {
        timeTrackerService.addTaskToTimeTracker(timeTrackerId, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeTracker(@PathVariable Integer id) {
        timeTrackerService.deleteTimeTracker(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{timeTrackerId}/tasks/{taskId}")
    public ResponseEntity<Void> removeTaskFromTimeTracker(@PathVariable Integer timeTrackerId, @PathVariable Integer taskId) {
        timeTrackerService.removeTaskFromTimeTracker(timeTrackerId, taskId);
        return ResponseEntity.ok().build();
    }

}
