package com.example.diploma.service;

import com.example.diploma.dto.CreateCommentRequest;
import com.example.diploma.dto.QueryCommentDto;
import com.example.diploma.dto.UpdateCommentRequest;
import com.example.diploma.entity.Comment;
import com.example.diploma.mapper.CommentMapper;
import com.example.diploma.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TaskService taskService;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public List<QueryCommentDto> getAllComments(Integer taskId) {
        return commentRepository.findByTaskId(taskId).stream()
                .map(commentMapper::toQueryDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Comment getCommentById(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find comment by id: %s".formatted(commentId)));
    }

    public QueryCommentDto saveComment(CreateCommentRequest request, Integer creatorId) {
        Comment comment = commentMapper.toEntity(request);
        comment.addUser(userService.findById(creatorId));
        comment.addTask(taskService.getTaskById(request.taskId()));
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toQueryDto(savedComment);
    }

    public QueryCommentDto updateComment(UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Failed to find comment by id: %s".formatted(request.id())));
        comment.setComment(request.comment());
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toQueryDto(savedComment);
    }

    public boolean deleteComment(Integer commentId, Integer userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find comment by id: %s".formatted(commentId)));
        if (comment.getUser().getId().equals(userId)) {
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

}
