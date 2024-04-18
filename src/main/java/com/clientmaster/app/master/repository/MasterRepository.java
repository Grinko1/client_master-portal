package com.clientmaster.app.master.repository;

import com.clientmaster.app.master.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterRepository extends JpaRepository<Master, Integer> {
    Optional<Master> findByUserId(Long id);
}
