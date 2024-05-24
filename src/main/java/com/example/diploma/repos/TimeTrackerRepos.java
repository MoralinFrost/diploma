package com.example.diploma.repos;

import com.example.diploma.Entity.TimeTracker;
import com.example.diploma.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTrackerRepos extends JpaRepository<TimeTracker, Integer> {
    List<TimeTracker> findAllByUser(User user);
}
