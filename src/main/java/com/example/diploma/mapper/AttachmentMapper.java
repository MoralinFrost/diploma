package com.example.diploma.mapper;

import com.example.diploma.dto.QueryAttachmentDto;
import com.example.diploma.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AttachmentMapper {

    QueryAttachmentDto toQueryDto(Attachment attachment);

}
