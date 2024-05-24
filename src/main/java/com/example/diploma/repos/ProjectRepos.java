package com.example.diploma.repos;

import com.example.diploma.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepos extends JpaRepository<Project, Integer> {
    List<Project> findAll();
    Optional<Project> findById(Integer id);
}
