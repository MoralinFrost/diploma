package com.example.diploma.service;

import com.example.diploma.Entity.Attachment;
import com.example.diploma.Entity.Comment;
import com.example.diploma.Entity.Task;
import com.example.diploma.repos.AttachmentRepos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepos attachmentRepos;

    public void saveAll(List<Attachment> attachments) {attachmentRepos.saveAll(attachments);}

    public void deleteAttachmentByTaskId(Integer id){
        List<Attachment> attachments = attachmentRepos.findByTaskId(id);
        attachmentRepos.deleteAll(attachments);
    }

    public List<Attachment> saveTask(List<MultipartFile> files, Task task){
        List<Attachment> attachments = new ArrayList<>();

        for (MultipartFile file : files) {
            Attachment attachment=new Attachment();
            attachment.setFilename(file.getOriginalFilename());
            attachment.setTask(task);
            attachments.add(attachment);

        }
        return attachmentRepos.saveAll(attachments);
    }

    public List<Attachment> saveComment(List<MultipartFile> files, Comment comment) {
        List<Attachment> attachments = new ArrayList<>();

        for (MultipartFile file : files) {
            Attachment attachment = new Attachment();
            attachment.setFilename(file.getOriginalFilename());
            attachment.setComment(comment);
            attachments.add(attachment);
        }

        return attachmentRepos.saveAll(attachments);
    }}
