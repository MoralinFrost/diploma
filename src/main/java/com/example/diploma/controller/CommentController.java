package com.example.diploma.controller;

import com.example.diploma.dto.CreateCommentRequest;
import com.example.diploma.dto.GetAllCommentResponse;
import com.example.diploma.dto.QueryCommentDto;
import com.example.diploma.dto.UpdateCommentRequest;
import com.example.diploma.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<GetAllCommentResponse> getAllComments(@RequestParam("taskId") Integer taskId) {
        return ResponseEntity.ok(new GetAllCommentResponse(commentService.getAllComments(taskId)));
    }

    @PostMapping
    public ResponseEntity<QueryCommentDto> createComment(@Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveComment(request));
    }

    @PutMapping
    public ResponseEntity<QueryCommentDto> updateComment(@Valid @RequestBody UpdateCommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
