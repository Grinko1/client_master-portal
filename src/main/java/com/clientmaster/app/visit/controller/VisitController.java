package com.clientmaster.app.visit.controller;


import com.clientmaster.app.visit.dto.VisitRequestDto;
import com.clientmaster.app.visit.dto.VisitResponseDto;
import com.clientmaster.app.visit.entity.Visit;
import com.clientmaster.app.visit.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visits")
public class VisitController {
    private final VisitService visitService;

    @GetMapping
    public List<VisitResponseDto> getAll (){
        return visitService.findAll();
    }
    @GetMapping("/{id}")
    public VisitResponseDto getById(@PathVariable("id") Integer id){
        return visitService.findById(id);
    }
    @PostMapping
    public VisitResponseDto save(@RequestBody VisitRequestDto visit){
        return visitService.saveOrUpdate(visit);
    }
    @PatchMapping("/{id}")
    public VisitResponseDto update(@PathVariable("id") Integer id,@RequestBody VisitRequestDto visit){
        visit.setId(id);
        return visitService.saveOrUpdate(visit);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Integer id){
        visitService.deleteById(id);
        return HttpStatus.OK;
    }

}
