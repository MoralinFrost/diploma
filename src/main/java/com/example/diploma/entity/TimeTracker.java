package com.example.diploma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "time_trackers")
public class TimeTracker {

    @Id
    @Column(name = "user_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "task_tracker",
            joinColumns = @JoinColumn(name = "tracker_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks;

    public void addTask(Task task) {
        tasks.add(task);
        task.getTimeTrackers().add(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.getTimeTrackers().remove(this);
    }

}
