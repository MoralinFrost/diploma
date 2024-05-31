package com.example.diploma.service;

import com.example.diploma.dto.MutableTaskDto;
import com.example.diploma.dto.QueryTaskDto;
import com.example.diploma.entity.Project;
import com.example.diploma.entity.Task;
import com.example.diploma.entity.User;
import com.example.diploma.mapper.TaskMapper;
import com.example.diploma.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskMapper taskMapper;

    @Transactional(readOnly = true)
    public List<QueryTaskDto> getAll(Integer namespaceId) {
        return taskRepository.findAllByProjectId(namespaceId).stream()
                .map(taskMapper::toQueryDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<QueryTaskDto> getAllByUserId(Integer namespaceId, Integer userId) {
        return taskRepository.findAllByProjectId(namespaceId).stream()
                .filter(task -> {
                    Integer assignedUserId = Optional.ofNullable(task.getAssignedUser())
                            .map(User::getId)
                            .orElse(null);
                    return userId.equals(assignedUserId);
                })
                .map(taskMapper::toQueryDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find task with id: %s".formatted(id)));
    }

    @Transactional(readOnly = true)
    public QueryTaskDto getQueryTaskDto(Integer id) {
        return taskRepository.findById(id)
                .map(taskMapper::toQueryDto)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find task with id: %s".formatted(id)));
    }

    public QueryTaskDto createTask(MutableTaskDto mutableTask) {
        if (mutableTask.id() != null) {
            throw new ValidationException("id must be null");
        }
        Project project = projectService.getProjectById(mutableTask.projectId());
        Task task = taskMapper.toEntity(mutableTask);
        task.addProject(project);
        if (mutableTask.userId() != null) {
            User user = userService.findById(mutableTask.userId());
            task.addAssignedUser(user);
        }
        Task savedTask = taskRepository.save(task);
        return taskMapper.toQueryDto(savedTask);
    }

    public QueryTaskDto updateTask(QueryTaskDto taskDto) {
        if (taskDto.id() == null) {
            throw new ValidationException("id can't be null");
        }
        Task entityTask = taskRepository.findById(taskDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Failed to find task with id: %s".formatted(taskDto.id())));
        Integer currentAssignUser = Optional.ofNullable(entityTask.getAssignedUser())
                .map(User::getId)
                .orElse(null);

        Project project = projectService.getProjectById(taskDto.project().id());
        Task task = taskMapper.toEntity(taskDto);
        task.addProject(project);

        if (taskDto.assignedUser() != null && !taskDto.assignedUser().id().equals(currentAssignUser)) {
            User user = userService.findById(taskDto.assignedUser().id());
            task.addAssignedUser(user);
        } else if (taskDto.assignedUser() == null) {
            task.removeAssignedUser(entityTask.getAssignedUser());
        }

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toQueryDto(updatedTask);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

}
