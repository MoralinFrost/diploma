package com.example.diploma.repos;

import com.example.diploma.Entity.Project;
import com.example.diploma.Entity.Task;
import com.example.diploma.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepos extends JpaRepository<Task, Integer> {
    Task getTaskById(Integer id);

    List<Task> findAllByPrincipal(User principal);

    List<Task> findByProject_Namespace(String namespace);

    @Query("Select t from Task t Join fetch t.project")
    List<Task> findAll();

    @Query("Select distinct t.project.namespace from Task t where t.project.namespace is not null")
    List<String> findNamespace();
}
