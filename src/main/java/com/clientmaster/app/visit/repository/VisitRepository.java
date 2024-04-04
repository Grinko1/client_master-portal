package com.clientmaster.app.visit.repository;

import com.clientmaster.app.visit.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
}
