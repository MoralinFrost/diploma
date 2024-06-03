package com.example.diploma.service;

import com.example.diploma.dto.ProjectDto;
import com.example.diploma.entity.Project;
import com.example.diploma.entity.User;
import com.example.diploma.mapper.ProjectMapper;
import com.example.diploma.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectMapper projectMapper;

    @Transactional(readOnly = true)
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find project with id: %s".formatted(id)));
    }

    @Transactional(readOnly = true)
    public ProjectDto getProjectDtoById(Integer id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find project with id: %s".formatted(id)));
    }

    public ProjectDto save(ProjectDto projectDto, Integer ownerId) {
        if (projectDto.id() != null) {
            throw new ValidationException("id must be empty");
        }
        Project project = projectMapper.toEntity(projectDto);
        project.setOwner(userService.findById(ownerId));
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    public ProjectDto update(ProjectDto projectDto) {
        if (projectDto.id() == null) {
            throw new ValidationException("id can't be empty");
        }
        Project project = projectMapper.toEntity(projectDto);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    public boolean deleteById(Integer id, Integer ownerId) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find project with id: %s".formatted(id)));
        if (project.getOwner().getId().equals(ownerId)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addUserToProject(Integer projectId, Integer userId) {
        User user = userService.findById(userId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find project with id: %s".formatted(projectId)));
        project.addUser(user);
        projectRepository.save(project);
    }

}
