package com.clientmaster.app.master.service;

import com.clientmaster.app.exceptions.NotFoundException;
import com.clientmaster.app.master.dto.MasterInfoDto;
import com.clientmaster.app.master.dto.MasterResponseDto;
import com.clientmaster.app.master.entity.Master;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterServiceMappedData {
    private final MasterService masterService;
    private final ModelMapper modelMapper;


    public List<MasterResponseDto> findAllWithVisits() {
        return masterService.findAll().stream().map((master) -> modelMapper.map(master, MasterResponseDto.class)).collect(Collectors.toList());
    }

    public List<MasterInfoDto> findAll() {
        return masterService.findAll().stream().map((master) -> modelMapper.map(master, MasterInfoDto.class)).collect(Collectors.toList());
    }

    public MasterResponseDto findByIdWithVisits(Integer id) {
        return modelMapper.map(masterService.findById(id), MasterResponseDto.class);
    }

    public MasterInfoDto findById(Integer id) {
        return modelMapper.map(masterService.findById(id), MasterInfoDto.class);
    }

    public MasterInfoDto save(Master master) {
        Master master1 = masterService.saveOrUpdate(master);
        MasterInfoDto response =  modelMapper.map(master1, MasterInfoDto.class);
        response.setUser_id(master1.getUser().getId());
        return response;
//        return modelMapper.map(masterService.saveOrUpdate(master), MasterInfoDto.class);
    }
    public MasterResponseDto update(Master master) {
        Master master1 = masterService.saveOrUpdate(master);
        MasterResponseDto response =  modelMapper.map(master1, MasterResponseDto.class);
        response.setUser_id(master1.getUser().getId());
        return response;
//        return modelMapper.map(masterService.saveOrUpdate(master), MasterResponseDto.class);
    }


    public void deleteById(Integer id) {
        masterService.deleteById(id);
    }
}
