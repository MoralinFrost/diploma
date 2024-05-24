package com.example.diploma.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class TimeTracker {
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    private LocalDate date;
    private String dayDescription;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="task_tracker",
        joinColumns = @JoinColumn(name="tracker_id"),
        inverseJoinColumns = @JoinColumn(name="task_id"))
    private Set<Task> tasks;
}
