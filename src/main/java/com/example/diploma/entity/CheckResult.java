package com.example.diploma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "check_results")
@EqualsAndHashCode(of = "id")
public class CheckResult {

    @Id
    @Column(name = "check_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "check_id", nullable = false)
    private Check check;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id", nullable = false)
    private Technology technology;

    @Column(name = "mark", nullable = false)
    private Integer mark;

}
