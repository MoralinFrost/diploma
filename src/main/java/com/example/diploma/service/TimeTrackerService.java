package com.example.diploma.service;

import com.example.diploma.dto.MutableTimeTrackerDto;
import com.example.diploma.dto.TimeTrackerDto;
import com.example.diploma.entity.Task;
import com.example.diploma.entity.TimeTracker;
import com.example.diploma.entity.User;
import com.example.diploma.mapper.TimeTrackerMapper;
import com.example.diploma.repository.TimeTrackerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeTrackerService {
    private final TimeTrackerRepository timeTrackerRepository;
    private final UserService userService;
    private final TaskService taskService;
    private final TimeTrackerMapper timeTrackerMapper;

    @Transactional(readOnly = true)
    public List<TimeTrackerDto> getAllTimeTrackers(Integer userId) {
        return timeTrackerRepository.findAllByUserId(userId).stream()
                .map(timeTrackerMapper::toDto)
                .toList();
    }

    public TimeTrackerDto createTimeTracker(MutableTimeTrackerDto timeTracker) {
        if (timeTracker.id() != null) {
            throw new ValidationException("id must be empty");
        }
        User user = userService.findById(timeTracker.userId());
        TimeTracker entity = timeTrackerMapper.toEntity(timeTracker);
        entity.setUser(user);
        TimeTracker savedTimeTracker = timeTrackerRepository.save(entity);
        return timeTrackerMapper.toDto(savedTimeTracker);
    }

    public TimeTrackerDto updateTimeTracker(MutableTimeTrackerDto timeTracker) {
        if (timeTracker.id() == null) {
            throw new ValidationException("id can't be empty");
        }
        User user = userService.findById(timeTracker.userId());
        TimeTracker entity = timeTrackerMapper.toEntity(timeTracker);
        entity.setUser(user);
        TimeTracker updatedTimeTracker = timeTrackerRepository.save(entity);
        return timeTrackerMapper.toDto(updatedTimeTracker);
    }

    public void deleteTimeTracker(Integer timeTrackerId) {
        timeTrackerRepository.deleteById(timeTrackerId);
    }

    public void addTaskToTimeTracker(Integer timeTrackerId, Integer taskId) {
        Task task = taskService.getTaskById(taskId);
        TimeTracker timeTracker = timeTrackerRepository.findById(timeTrackerId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find time tracker with id: %s".formatted(timeTrackerId)));
        timeTracker.addTask(task);
        timeTrackerRepository.save(timeTracker);
    }

    public void removeTaskFromTimeTracker(Integer timeTrackerId, Integer taskId) {
        Task task = taskService.getTaskById(taskId);
        TimeTracker timeTracker = timeTrackerRepository.findById(timeTrackerId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find time tracker with id: %s".formatted(timeTrackerId)));
        timeTracker.removeTask(task);
        timeTrackerRepository.save(timeTracker);
    }

}
