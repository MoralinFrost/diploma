package com.example.diploma.entity;

import com.example.diploma.dto.Priority;
import com.example.diploma.dto.Workflow;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks")
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "deadline_date")
    private Timestamp deadlineDate;

    @Column(name = "created_date")
    private Timestamp createdDate = Timestamp.from(Instant.now());

    @Column(name = "closed_date")
    private Timestamp closedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_id")
    private User assignedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(name = "workflow")
    private Workflow workflow;

    @ManyToMany(mappedBy = "tasks")
    private Set<TimeTracker> timeTrackers;

    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE})
    private Set<Attachment> attachments;

    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE})
    private Set<Comment> comments;

    public void addAssignedUser(User user) {
        this.assignedUser = user;
        user.getTasks().add(this);
    }

    public void removeAssignedUser(User user) {
        this.assignedUser = null;
        user.getTasks().remove(this);
    }

    public void addProject(Project project) {
        this.project = project;
        project.getTasks().add(this);
    }

}
