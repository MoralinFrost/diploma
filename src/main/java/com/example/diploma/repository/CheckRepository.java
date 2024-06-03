package com.example.diploma.repository;

import com.example.diploma.entity.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepository extends JpaRepository<Check, Integer> {

}
