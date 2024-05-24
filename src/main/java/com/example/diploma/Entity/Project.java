package com.example.diploma.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Project {
    @Id
    private Integer id;

    private String namespace;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public Project(String namespace) {
        this.namespace = namespace;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="project_user",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<User> users;

    public Project(){}
}
