package com.example.diploma.mapper;

import com.example.diploma.dto.CreateCommentRequest;
import com.example.diploma.dto.QueryCommentDto;
import com.example.diploma.dto.UpdateCommentRequest;
import com.example.diploma.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UserMapper.class, AttachmentMapper.class}
)
public interface CommentMapper {

    QueryCommentDto toQueryDto(Comment comment);

    Comment toEntity(CreateCommentRequest request);

    Comment toEntity(UpdateCommentRequest request);

}
