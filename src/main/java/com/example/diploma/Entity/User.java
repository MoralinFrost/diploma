package com.example.diploma.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String grade;
    private String department;
    private String email;
    private String password;
    private String photo;
    private String activationCode;

    private boolean status;


    @Enumerated(EnumType.STRING)
    private Role roles;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Project> projects;

}
