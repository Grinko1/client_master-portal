package com.clientmaster.app.visit.controller;


import com.clientmaster.app.visit.dto.VisitRequestDto;
import com.clientmaster.app.visit.dto.VisitResponseDto;
import com.clientmaster.app.visit.entity.Visit;
import com.clientmaster.app.visit.service.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visits")
@Tag(name = "Визит")
public class VisitController {
    private final VisitService visitService;

    @Operation(summary = "Получение всех визитов")
    @GetMapping
    public List<VisitResponseDto> getAll (){
        System.out.println("all visit");
        return visitService.findAll();
    }
    @Operation(summary = "Получение визита по id")
    @GetMapping("/{id}")
    public VisitResponseDto getById(@PathVariable("id") Integer id){
        return visitService.findById(id);
    }
    @Operation(summary = "Добавление визита")
    @PostMapping
    public VisitResponseDto save(@RequestBody VisitRequestDto visit){
        return visitService.saveOrUpdate(visit);
    }
    @Operation(summary = "Обновление визита")
    @PatchMapping("/{id}")
    public VisitResponseDto update(@PathVariable("id") Integer id,@RequestBody VisitRequestDto visit){
        visit.setId(id);
        return visitService.saveOrUpdate(visit);
    }
    @Operation(summary = "Удаление визита")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Integer id){
        visitService.deleteById(id);
        return HttpStatus.OK;
    }

}
