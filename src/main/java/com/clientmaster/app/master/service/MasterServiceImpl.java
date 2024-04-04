package com.clientmaster.app.master.service;

import com.clientmaster.app.exceptions.NotFoundException;
import com.clientmaster.app.master.dto.MasterResponseDto;
import com.clientmaster.app.master.entity.Master;
import com.clientmaster.app.master.repository.MasterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Master> findAll() {
        return masterRepository.findAll();
    }

    @Override
    public Master findById(Integer id) {
        return masterRepository.findById(id).orElseThrow(() -> new NotFoundException("Master", "masterId", id));

    }

    @Override
    public Master saveOrUpdate(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public void deleteById(Integer id) {
        masterRepository.deleteById(id);
    }
}
