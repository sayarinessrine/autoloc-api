package com.autoloc.api.repository;

import com.autoloc.api.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
}