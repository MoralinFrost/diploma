package com.example.diploma.service;

import com.example.diploma.Entity.Task;
import com.example.diploma.Entity.TimeTracker;
import com.example.diploma.Entity.User;
import com.example.diploma.repos.TimeTrackerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TimeTrackerService {

    @Autowired
    private TimeTrackerRepos timeTrackerRepos;

    public List<TimeTracker> getAllTimeTrackers(User user) {
        return timeTrackerRepos.findAllByUser(user);
    }

    public TimeTracker addTimeTracker(User user, LocalDate date, String dayDescription, Set<Task> tasks) {
     TimeTracker timeTracker = new TimeTracker();
     timeTracker.setUser(user);
     timeTracker.setDate(date);
     timeTracker.setDayDescription(dayDescription);
     timeTracker.setTasks(tasks);
     return timeTrackerRepos.save(timeTracker);
    }

    public Optional<TimeTracker> getTimeTrackerById(Integer id) {
        return timeTrackerRepos.findById(id);
    }

    public TimeTracker updateTimeTracker(Integer id, User user, LocalDate date, String dayDescription, Set<Task> tasks) {
    Optional<TimeTracker> optionalTimeTracker = timeTrackerRepos.findById(id);
    if(optionalTimeTracker.isPresent()) {
        TimeTracker timeTracker = optionalTimeTracker.get();
        timeTracker.setUser(user);
        timeTracker.setDate(date);
        timeTracker.setDayDescription(dayDescription);
        timeTracker.setTasks(tasks);
        return timeTrackerRepos.save(timeTracker);
    }
    return null;

    }

    public void deleteTimeTracker(Integer id) {
        timeTrackerRepos.deleteById(id);
    }
}
