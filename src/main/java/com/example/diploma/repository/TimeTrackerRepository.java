package com.example.diploma.repository;

import com.example.diploma.entity.TimeTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTrackerRepository extends JpaRepository<TimeTracker, Integer> {

    List<TimeTracker> findAllByUserId(Integer userId);

}
