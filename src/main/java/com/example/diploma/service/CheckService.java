package com.example.diploma.service;

import com.example.diploma.dto.MutableCheckDto;
import com.example.diploma.dto.QueryCheckDto;
import com.example.diploma.entity.Check;
import com.example.diploma.mapper.CheckMapper;
import com.example.diploma.repository.CheckRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckService {
    private final CheckRepository checkRepository;
    private final UserService userService;
    private final CheckMapper checkMapper;

    @Transactional(readOnly = true)
    public List<QueryCheckDto> getAllChecks(Integer intervieweeId, Integer interviewerId) {
        return checkRepository.findAll().stream()
                .filter(check -> {
                    if (intervieweeId != null) {
                        return check.getInterviewee().getId().equals(intervieweeId);
                    }
                    return true;
                })
                .filter(check -> {
                    if (interviewerId != null) {
                        return check.getInterviewee().getId().equals(interviewerId);
                    }
                    return true;
                })
                .map(checkMapper::toQueryDto)
                .toList();
    }

    public QueryCheckDto createCheck(MutableCheckDto checkDto) {
        if (checkDto.id() != null) {
            throw new ValidationException("id must be empty");
        }
        Check check = checkMapper.toEntity(checkDto);
        check.setInterviewee(userService.findById(checkDto.intervieweeId()));
        check.setInterviewer(userService.findById(checkDto.interviewerId()));
        Check savedCheck = checkRepository.save(check);
        return checkMapper.toQueryDto(savedCheck);
    }

    public QueryCheckDto updateCheck(MutableCheckDto checkDto) {
        if (checkDto.id() == null) {
            throw new ValidationException("id must be present");
        }
        Check check = checkMapper.toEntity(checkDto);
        check.setInterviewee(userService.findById(checkDto.intervieweeId()));
        check.setInterviewer(userService.findById(checkDto.interviewerId()));
        Check savedCheck = checkRepository.save(check);
        return checkMapper.toQueryDto(savedCheck);
    }

    public void deleteCheck(Integer checkId) {
        checkRepository.deleteById(checkId);
    }

}
