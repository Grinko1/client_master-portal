package com.clientmaster.app.client.service;

import com.clientmaster.app.client.dto.ClientResponseDto;
import com.clientmaster.app.client.entity.Client;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface ClientService {
    List<Client> findAll();
    Client findById(Integer id);
    Client saveOrUpdate(Client client);
    void deleteById(Integer id);
    Client findByUserId(Long id);
}
