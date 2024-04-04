package com.clientmaster.app.visit.service;

import com.clientmaster.app.visit.dto.VisitRequestDto;
import com.clientmaster.app.visit.dto.VisitResponseDto;
import com.clientmaster.app.visit.entity.Visit;

import java.text.ParseException;
import java.util.List;

public interface VisitService {
    List<VisitResponseDto> findAll();
    VisitResponseDto findById(Integer id);
    VisitResponseDto saveOrUpdate(VisitRequestDto visit);
    void deleteById(Integer id);
}
