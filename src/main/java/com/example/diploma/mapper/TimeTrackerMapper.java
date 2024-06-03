package com.example.diploma.mapper;

import com.example.diploma.dto.MutableTimeTrackerDto;
import com.example.diploma.dto.TimeTrackerDto;
import com.example.diploma.entity.TimeTracker;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {TaskMapper.class, UserMapper.class}
)
public interface TimeTrackerMapper {

    TimeTrackerDto toDto(TimeTracker timeTracker);

    TimeTracker toEntity(MutableTimeTrackerDto timeTrackerDto);

}
