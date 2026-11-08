package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.exception.BadRequestException;
import com.autoloc.api.repository.VehiculeRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.VehiculeService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class VehiculeServiceImpl extends AbstractCrudService<Vehicule, Long> implements VehiculeService {

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository) {
        super(vehiculeRepository, "Véhicule");
    }

    @Override
    protected void assignId(Vehicule vehicule, Long id) {
        vehicule.setIdVehicule(id);
    }

    @Override
    protected void validate(Vehicule vehicule) {
        if (vehicule.getTarifJournalier() == null
                || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Le tarif journalier doit être strictement positif");
        }
    }
}
