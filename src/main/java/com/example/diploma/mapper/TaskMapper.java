package com.example.diploma.mapper;

import com.example.diploma.dto.MutableTaskDto;
import com.example.diploma.dto.QueryTaskDto;
import com.example.diploma.dto.Workflow;
import com.example.diploma.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UserMapper.class}
)
public interface TaskMapper {

    QueryTaskDto toQueryDto(Task task);

    Task toEntity(MutableTaskDto mutableTaskDto);

    @Mapping(target = "closedDate", source = "taskDto")
    Task toEntity(QueryTaskDto taskDto);

    default Timestamp mapClosedDate(QueryTaskDto taskDto) {
        return taskDto.workflow().equals(Workflow.DONE) ? Timestamp.from(Instant.now()) : null;
    }

    default Timestamp mapTimestamp(LocalDateTime value) {
        return value != null ? Timestamp.valueOf(value) : null;
    }

}
