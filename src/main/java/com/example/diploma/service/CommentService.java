package com.example.diploma.service;

import com.example.diploma.Entity.Attachment;
import com.example.diploma.Entity.Comment;
import com.example.diploma.Entity.Task;
import com.example.diploma.Entity.User;
import com.example.diploma.repos.CommentRepos;
import com.example.diploma.repos.TaskRepos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Service
public class CommentService {
    @Autowired
    private CommentRepos commentRepos;
    @Autowired
    private TaskRepos taskRepos;
    @Autowired
    private AttachmentService attachmentService;

    public Comment addCommentToTask(Integer taskId, String commentDescription, User user, List<MultipartFile> files) {
        Task task=taskRepos.findById(taskId).orElseThrow(()-> new RuntimeException("Task not found"));
        Comment comment=new Comment();
        comment.setCrt_date(LocalDate.now());
        comment.setComment_description(commentDescription);
        comment.setTask(task);
        comment.setCommenter(user);

        comment=commentRepos.save(comment);

        List<Attachment> attachments=attachmentService.saveComment(files,comment);
        comment.setAttachments(attachments);

        task.getComments().add(comment);
        taskRepos.save(task);

        return comment;
    }
}
