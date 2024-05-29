package com.example.diploma.service;

import com.example.diploma.dto.QueryAttachmentDto;
import com.example.diploma.entity.Attachment;
import com.example.diploma.entity.Comment;
import com.example.diploma.entity.Task;
import com.example.diploma.mapper.AttachmentMapper;
import com.example.diploma.repository.AttachmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final TaskService taskService;
    private final CommentService commentService;
    private final AttachmentMapper attachmentMapper;

    @Transactional(readOnly = true)
    public List<QueryAttachmentDto> getAllAttachments(Integer taskId) {
        return attachmentRepository.findAllByTaskId(taskId).stream()
                .map(attachmentMapper::toQueryDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Attachment getAttachmentById(Integer attachmentId) {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find attachment with id: %s".formatted(attachmentId)));
    }

    @SneakyThrows
    public void uploadAttachment(MultipartFile file, Integer taskId, Integer commentId) {
        if (taskId == null && commentId == null) {
            throw new ValidationException("taskId or commentId must be present");
        }

        Attachment attachment = new Attachment();
        attachment.setFilename(file.getOriginalFilename());
        attachment.setData(file.getBytes());

        if (taskId != null) {
            Task task = taskService.getTaskById(taskId);
            attachment.addTask(task);
        } else {
            Comment comment = commentService.getCommentById(commentId);
            attachment.addComment(comment);
        }

        attachmentRepository.save(attachment);
    }

    public void deleteAttachment(Integer attachmentId) {
        attachmentRepository.deleteById(attachmentId);
    }
}
