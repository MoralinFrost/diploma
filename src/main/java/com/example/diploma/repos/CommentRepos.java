package com.example.diploma.repos;

import com.example.diploma.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepos extends JpaRepository<Comment, Integer> {
    List<Comment> findByTaskId(int task_id);
}
