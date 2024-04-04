package com.clientmaster.app.client.controller;

import com.clientmaster.app.client.dto.ClientInfoDto;
import com.clientmaster.app.client.dto.ClientResponseDto;
import com.clientmaster.app.client.entity.Client;
import com.clientmaster.app.client.service.ClientService;
import com.clientmaster.app.client.service.ClientServiceMappedData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientServiceMappedData clientService;
    private final ModelMapper modelMapper;

    @GetMapping("/visits")
    public List<ClientResponseDto> getAllWithVisits(){
        return clientService.findAllWithVisits();
    }
    @GetMapping
    public List<ClientInfoDto> getAll(){
        return clientService.findAll();
    }
    @GetMapping("/{id}")
    public ClientInfoDto getById(@PathVariable("id") Integer id){
        return clientService.findById(id);
    }
    @GetMapping("/{id}/visits")
    public ClientResponseDto  getByIdWithVisits(@PathVariable("id") Integer id){
        return clientService.findByIdWithVisits(id);
    }
    @PostMapping
    public ClientInfoDto save(@RequestBody Client client){
        return clientService.saveOrUpdate(client);
    }
    @PatchMapping("/{id}")
    public ClientInfoDto update(@PathVariable("id") Integer id,@RequestBody Client client ){
        client.setId(id);
        return clientService.saveOrUpdate(client);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Integer id){
        clientService.deleteById(id);
        return HttpStatus.OK;
    }


}
