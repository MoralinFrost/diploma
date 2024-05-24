package com.example.diploma.repos;

import com.example.diploma.Entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepos extends JpaRepository<Attachment, Integer> {
    List<Attachment> findByTaskId(Integer id);
}
