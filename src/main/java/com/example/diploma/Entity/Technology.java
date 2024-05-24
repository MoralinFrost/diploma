package com.example.diploma.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TechnologyType tech_type;
    private String tech_name;

    public Technology(){}

    public Technology(TechnologyType tech_type, String tech_name)
    {
        this.tech_name=tech_name;
        this.tech_type=tech_type;
    }


}
