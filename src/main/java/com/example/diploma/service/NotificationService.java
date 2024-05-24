package com.example.diploma.service;

import com.example.diploma.Entity.Notification;
import com.example.diploma.Entity.User;
import com.example.diploma.repos.NotificationRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private NotificationRepos notificationRepos;

    public List<Notification> getAllNotifications(User user) {
        return notificationRepos.findAllByRecipent(user);
    }

    public void deleteNotification(Integer notificationId) {
        notificationRepos.deleteById(notificationId);
    }
}
