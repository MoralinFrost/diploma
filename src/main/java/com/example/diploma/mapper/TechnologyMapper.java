package com.example.diploma.mapper;

import com.example.diploma.dto.MutableTechnologyDto;
import com.example.diploma.dto.QueryTechnologyDto;
import com.example.diploma.entity.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TechnologyMapper {

    QueryTechnologyDto toQueryDto(Technology technology);

    Technology toEntity(MutableTechnologyDto technologyDto);

}
