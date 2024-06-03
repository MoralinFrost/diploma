package com.example.diploma.controller;

import com.example.diploma.dto.GetAllAttachmentResponse;
import com.example.diploma.entity.Attachment;
import com.example.diploma.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachments")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping
    public ResponseEntity<GetAllAttachmentResponse> getAllAttachments(@RequestParam("taskId") Integer taskId) {
        return ResponseEntity.ok(new GetAllAttachmentResponse(attachmentService.getAllAttachments(taskId)));
    }

    @SneakyThrows
    @GetMapping(value = "/{id}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Void> download(@PathVariable Integer id, HttpServletResponse response) {
        Attachment attachment = attachmentService.getAttachmentById(id);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"%s\"".formatted(attachment.getFilename()));

        response.getOutputStream().write(attachment.getData());
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadAttachment(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "taskId", required = false) Integer taskId,
            @RequestParam(value = "commentId", required = false) Integer commentId
    ) {
        attachmentService.uploadAttachment(file, taskId, commentId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Integer id) {
        attachmentService.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }

}
