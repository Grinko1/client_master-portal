package com.clientmaster.app.master.controller;

import com.clientmaster.app.master.dto.MasterInfoDto;
import com.clientmaster.app.master.dto.MasterResponseDto;
import com.clientmaster.app.master.entity.Master;
import com.clientmaster.app.master.service.MasterService;
import com.clientmaster.app.master.service.MasterServiceMappedData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/masters")
public class MasterController {
    private final MasterServiceMappedData masterService;

    @GetMapping
    public List<MasterInfoDto> getAll(){
        return  masterService.findAll();
    }
    @GetMapping("/visits")
    public List<MasterResponseDto> getAllWithVisits(){
        return  masterService.findAllWithVisits();
    }

    @GetMapping("/{id}/visits")
    public MasterResponseDto getByIdWithVisits(@PathVariable("id") Integer id){
        return masterService.findByIdWithVisits(id);
    }
    @GetMapping("/{id}/")
    public MasterInfoDto getById(@PathVariable("id") Integer id){
        return masterService.findById(id);
    }
    @PostMapping
    public MasterInfoDto save(@RequestBody Master client){
        return masterService.save(client);
    }
    @PatchMapping("/{id}")
    public MasterResponseDto update(@PathVariable("id") Integer id,@RequestBody Master master ){
        master.setId(id);
        return masterService.update(master);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Integer id){
        masterService.deleteById(id);
        return HttpStatus.OK;
    }
 }
