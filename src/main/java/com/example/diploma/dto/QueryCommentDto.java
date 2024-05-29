package com.example.diploma.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record QueryCommentDto(
        Integer id,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdDate,
        String comment,
        UserDto user,
        List<QueryAttachmentDto> attachments
) {
}
