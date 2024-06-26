package com.clientmaster.app.client.controller;

import com.clientmaster.app.client.dto.ClientInfoDto;
import com.clientmaster.app.client.dto.ClientResponseDto;
import com.clientmaster.app.client.entity.Client;
import com.clientmaster.app.client.service.ClientService;
import com.clientmaster.app.client.service.ClientServiceMappedData;
import com.clientmaster.app.user.entity.User;
import com.clientmaster.app.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
@Tag(name = "Клиент")
public class ClientController {
    private final ClientServiceMappedData clientService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Operation(summary = "Получение клиентов с визитами")
    @GetMapping("/visits")
    public List<ClientResponseDto> getAllWithVisits() {
        return clientService.findAllWithVisits();
    }

    @Operation(summary = "Получение клиентов")
    @GetMapping
    public List<ClientInfoDto> getAll() {
        return clientService.findAll();
    }

    @Operation(summary = "Получение клиента по id")
    @GetMapping("/{id}")
    public ClientInfoDto getById(@PathVariable("id") Integer id) {
        return clientService.findById(id);
    }

    @Operation(summary = "Получение клиента по id с массивом визитов")
    @GetMapping("/{id}/visits")
    public ClientResponseDto getByIdWithVisits(@PathVariable("id") Integer id) {
        return clientService.findByIdWithVisits(id);
    }

    @Operation(summary = "Добавление клиента")
    @PostMapping
    public ClientInfoDto save(@RequestBody ClientInfoDto client) {
        Long userId = client.getUser_id();
        User user = userRepository.findById(userId).orElse(null);
        Client newClient = modelMapper.map(client, Client.class);
        newClient.setUser(user);
        return clientService.saveOrUpdate(newClient);
    }

    @Operation(summary = "Обновление профиля клиента")
    @PatchMapping("/{id}")
    public ClientInfoDto update(@PathVariable("id") Integer id, @RequestBody ClientInfoDto client) {
        client.setId(id);
        Long userId = client.getUser_id();
        User user = userRepository.findById(userId).orElse(null);
        Client newClient = modelMapper.map(client, Client.class);
        newClient.setUser(user);
        return clientService.saveOrUpdate(newClient);
    }

    @Operation(summary = "Удаление клиента по id")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Integer id) {
        clientService.deleteById(id);
        return HttpStatus.OK;
    }


}
