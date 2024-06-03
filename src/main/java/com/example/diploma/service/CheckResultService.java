package com.example.diploma.service;

import com.example.diploma.mapper.CheckResultMapper;
import com.example.diploma.repository.CheckResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckResultService {
    private final CheckResultRepository checkResultRepository;
    private final CheckResultMapper checkResultMapper;


}
