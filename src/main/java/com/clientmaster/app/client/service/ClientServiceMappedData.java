package com.clientmaster.app.client.service;

import com.clientmaster.app.client.dto.ClientInfoDto;
import com.clientmaster.app.client.dto.ClientResponseDto;
import com.clientmaster.app.client.entity.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceMappedData {
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    public List<ClientInfoDto> findAll() {
        return clientService.findAll().stream().map((client) -> modelMapper.map(client, ClientInfoDto.class)).collect(Collectors.toList());
    }
    public List<ClientResponseDto> findAllWithVisits() {
        return clientService.findAll().stream().map((client) -> modelMapper.map(client, ClientResponseDto.class)).collect(Collectors.toList());
    }

    public ClientResponseDto findByIdWithVisits(Integer id) {
        Client client = clientService.findById(id);
        System.out.println(client.getVisits());
        return modelMapper.map(client, ClientResponseDto.class);
    }

    public ClientInfoDto findById(Integer id) {
        Client client = clientService.findById(id);
        return modelMapper.map(client, ClientInfoDto.class);
    }

    public ClientInfoDto saveOrUpdate(Client client) {
        Client clientNew = clientService.saveOrUpdate(client);
        ClientInfoDto dto = modelMapper.map(clientNew, ClientInfoDto.class);
        dto.setUser_id(clientNew.getUser().getId());
        return dto;
    }

    public void deleteById(Integer id) {
        clientService.deleteById(id);
    }

}
