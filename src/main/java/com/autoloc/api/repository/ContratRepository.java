package com.autoloc.api.repository;

import com.autoloc.api.domain.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
}
