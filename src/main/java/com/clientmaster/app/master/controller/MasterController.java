package com.clientmaster.app.master.controller;

import com.clientmaster.app.master.dto.MasterInfoDto;
import com.clientmaster.app.master.dto.MasterResponseDto;
import com.clientmaster.app.master.entity.Master;
import com.clientmaster.app.master.service.MasterService;
import com.clientmaster.app.master.service.MasterServiceMappedData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/masters")
@Tag(name = "Мастер")
public class MasterController {
    private final MasterServiceMappedData masterService;

    @Operation(summary = "Получение мастеров")
    @GetMapping
    public List<MasterInfoDto> getAll(){
        return  masterService.findAll();
    }
    @Operation(summary = "Получение мастеров с визитами")
    @GetMapping("/visits")
    public List<MasterResponseDto> getAllWithVisits(){
        return  masterService.findAllWithVisits();
    }
    @Operation(summary = "Получение мастера с визитами")
    @GetMapping("/{id}/visits")
    public MasterResponseDto getByIdWithVisits(@PathVariable("id") Integer id){
        return masterService.findByIdWithVisits(id);
    }
    @Operation(summary = "Получение мастера")
    @GetMapping("/{id}/")
    public MasterInfoDto getById(@PathVariable("id") Integer id){
        return masterService.findById(id);
    }
    @Operation(summary = "Добавление мастера")
    @PostMapping
    public MasterInfoDto save(@RequestBody Master client){
        return masterService.save(client);
    }
    @Operation(summary = "Обновление профиля мастера")
    @PatchMapping("/{id}")
    public MasterResponseDto update(@PathVariable("id") Integer id,@RequestBody Master master ){
        master.setId(id);
        return masterService.update(master);
    }
    @Operation(summary = "Удаления мастера по id")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Integer id){
        masterService.deleteById(id);
        return HttpStatus.OK;
    }
 }
