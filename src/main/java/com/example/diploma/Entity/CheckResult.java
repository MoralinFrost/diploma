package com.example.diploma.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CheckResult {
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="check_id")
    private Check check;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="technology_id")
    private Technology technology;

    private int mark;
}
