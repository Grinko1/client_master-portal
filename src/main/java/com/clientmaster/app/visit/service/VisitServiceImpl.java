package com.clientmaster.app.visit.service;

import com.clientmaster.app.exceptions.NotFoundException;
import com.clientmaster.app.visit.dto.VisitRequestDto;
import com.clientmaster.app.visit.dto.VisitResponseDto;
import com.clientmaster.app.visit.entity.Visit;
import com.clientmaster.app.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<VisitResponseDto> findAll() {
        return visitRepository.findAll().stream().map((visit) -> modelMapper.map(visit, VisitResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public VisitResponseDto findById(Integer id) {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new NotFoundException("Visit", "visitId", id));
        return modelMapper.map(visit, VisitResponseDto.class);
    }

    @Override
    public VisitResponseDto saveOrUpdate(VisitRequestDto dto) {
        Visit visit = modelMapper.map(dto, Visit.class);
        return modelMapper.map(visitRepository.save(visit), VisitResponseDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        visitRepository.deleteById(id);
    }
}
