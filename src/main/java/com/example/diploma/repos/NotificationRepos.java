package com.example.diploma.repos;

import com.example.diploma.Entity.Notification;
import com.example.diploma.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepos extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByRecipent(User user);


}
