package com.clientmaster.app.visit.repository;

import com.clientmaster.app.visit.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
    List<Visit> findByClientId(Integer id);
    List<Visit> findByMasterId(Integer id);
}
