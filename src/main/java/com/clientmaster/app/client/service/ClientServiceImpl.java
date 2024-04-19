package com.clientmaster.app.client.service;

import com.clientmaster.app.client.dto.ClientInfoDto;
import com.clientmaster.app.client.dto.ClientResponseDto;
import com.clientmaster.app.client.entity.Client;
import com.clientmaster.app.client.repository.ClientRepository;
import com.clientmaster.app.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client", "client id", id));
    }

    @Override
    public Client saveOrUpdate(Client client) {
        return clientRepository.save(client);
    }


    @Override
    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findByUserId(Long userId) {
        return clientRepository.findByUserId(userId).orElse(null);
    }
}
