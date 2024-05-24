package com.example.diploma.service;

import com.example.diploma.Entity.Project;
import com.example.diploma.Entity.User;
import com.example.diploma.repos.ProjectRepos;
import com.example.diploma.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepos projectRepos;

    @Autowired
    private UserRepos userRepos;

    public List<Project> getAllProjects() {

        return projectRepos.findAll();
    }

    public Optional<Project> getProjectById(int id) {return projectRepos.findById(id);}

    public Project createProject(Project project) {return projectRepos.save(project);}

    public void deleteProjectById(Integer id){projectRepos.deleteById(id);}

    public Optional<Project> findProjectById(int id) {return projectRepos.findById(id);}

    public void addUserToProject(Project project, User user) {
        project.getUsers().add(user);
        user.getProjects().add(project);
        projectRepos.save(project);
        userRepos.save(user);
    }
}
