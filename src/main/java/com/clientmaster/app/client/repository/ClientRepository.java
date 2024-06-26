package com.clientmaster.app.client.repository;

import com.clientmaster.app.client.dto.ClientInfoDto;
import com.clientmaster.app.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByUserId(Long id);
}
