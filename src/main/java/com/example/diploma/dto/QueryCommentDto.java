package com.example.diploma.dto;

import java.time.LocalDateTime;
import java.util.List;

public record QueryCommentDto(
        Integer id,
        LocalDateTime createdDate,
        String comment,
        UserDto user,
        List<QueryAttachmentDto> attachments
) {
}
