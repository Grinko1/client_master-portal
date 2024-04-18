package com.clientmaster.app.visit.service;

import com.clientmaster.app.exceptions.NotFoundException;
import com.clientmaster.app.visit.dto.VisitRequestDto;
import com.clientmaster.app.visit.dto.VisitResponseDto;
import com.clientmaster.app.visit.entity.Visit;
import com.clientmaster.app.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<VisitResponseDto> findAll() {
        return visitRepository.findAll().stream()
                .map(this::mapVisitToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public VisitResponseDto findById(Integer id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Visit", "visitId", id));
        return mapVisitToResponseDto(visit);
    }

    @Override
    public VisitResponseDto saveOrUpdate(VisitRequestDto dto) {
        Visit visit = modelMapper.map(dto, Visit.class);
        return mapVisitToResponseDto(visitRepository.save(visit));
    }

    @Override
    public void deleteById(Integer id) {
        visitRepository.deleteById(id);
    }

    private VisitResponseDto mapVisitToResponseDto(Visit visit) {
        VisitResponseDto responseDto = modelMapper.map(visit, VisitResponseDto.class);

        // Convert time to UTC timezone
        TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(utcTimeZone);
        String utcTime = sdf.format(visit.getTime());

        try {
            // Create a Date object with the UTC time
            Date timeInUtc = sdf.parse(utcTime);
            responseDto.setTime(timeInUtc);
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
        }

        return responseDto;
    }
}
