package com.clientmaster.app.master.service;

import com.clientmaster.app.master.dto.MasterResponseDto;
import com.clientmaster.app.master.entity.Master;

import java.util.List;
import java.util.Optional;

public interface MasterService {
    List<Master> findAll();
    Master findById(Integer id);
    Master saveOrUpdate(Master master);
   Master findByUserId(Long id);
    void deleteById(Integer id);
}
