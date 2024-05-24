package com.example.diploma.repos;

import com.example.diploma.Entity.Check;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepos extends JpaRepository<Check, Integer> {
}
