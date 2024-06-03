package com.example.diploma.repository;

import com.example.diploma.entity.CheckResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckResultRepository extends JpaRepository<CheckResult, Integer> {
}
