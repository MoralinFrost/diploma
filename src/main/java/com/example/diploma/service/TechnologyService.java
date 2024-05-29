package com.example.diploma.service;

import com.example.diploma.dto.MutableTechnologyDto;
import com.example.diploma.dto.QueryTechnologyDto;
import com.example.diploma.entity.Technology;
import com.example.diploma.mapper.TechnologyMapper;
import com.example.diploma.repository.TechnologyRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final TechnologyMapper technologyMapper;

    @Transactional(readOnly = true)
    public List<QueryTechnologyDto> getAllTechnologies() {
        return technologyRepository.findAll().stream()
                .map(technologyMapper::toQueryDto)
                .toList();
    }

    public QueryTechnologyDto createTechnology(MutableTechnologyDto technologyDto) {
        if (technologyDto.id() != null) {
            throw new ValidationException("id must be empty");
        }
        Technology entity = technologyMapper.toEntity(technologyDto);
        Technology savedTechnology = technologyRepository.save(entity);
        return technologyMapper.toQueryDto(savedTechnology);
    }

    public QueryTechnologyDto updateTechnology(MutableTechnologyDto technologyDto) {
        if (technologyDto.id() == null) {
            throw new ValidationException("id must be present");
        }
        Technology entity = technologyMapper.toEntity(technologyDto);
        Technology savedTechnology = technologyRepository.save(entity);
        return technologyMapper.toQueryDto(savedTechnology);
    }

    public void deleteTechnology(Integer technologyId) {
        technologyRepository.deleteById(technologyId);
    }

}
