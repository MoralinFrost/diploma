package com.example.diploma.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private LocalDate crt_date;
    private String comment_description;

    @OneToMany(mappedBy = "comment")
    private List<Attachment> attachments;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="task_id")
    private  Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User commenter;

    public Comment() {

    }
}
