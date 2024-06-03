package com.example.diploma.mapper;

import com.example.diploma.dto.MutableCheckDto;
import com.example.diploma.dto.QueryCheckDto;
import com.example.diploma.entity.Check;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UserMapper.class}
)
public interface CheckMapper {

    QueryCheckDto toQueryDto(Check check);

    Check toEntity(MutableCheckDto mutableCheckDto);

    default Timestamp mapTimestamp(LocalDateTime value) {
        return value != null ? Timestamp.valueOf(value) : null;
    }

}
