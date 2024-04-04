package com.clientmaster.app.client.service;

import com.clientmaster.app.client.dto.ClientResponseDto;
import com.clientmaster.app.client.entity.Client;

import java.util.List;


public interface ClientService {
    List<Client> findAll();
    Client findById(Integer id);
    Client saveOrUpdate(Client client);
    void deleteById(Integer id);
}
