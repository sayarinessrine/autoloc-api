package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Equipement;
import com.autoloc.api.repository.EquipementRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.EquipementService;
import org.springframework.stereotype.Service;

@Service
public class EquipementServiceImpl extends AbstractCrudService<Equipement, Long> implements EquipementService {

    public EquipementServiceImpl(EquipementRepository equipementRepository) {
        super(equipementRepository, "Équipement");
    }

    @Override
    protected void assignId(Equipement equipement, Long id) {
        equipement.setIdEquipement(id);
    }
}
