package com.autoloc.api.repository;

import com.autoloc.api.domain.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
