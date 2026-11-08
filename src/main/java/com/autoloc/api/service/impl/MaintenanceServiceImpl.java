package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Maintenance;
import com.autoloc.api.exception.BadRequestException;
import com.autoloc.api.repository.MaintenanceRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.MaintenanceService;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceServiceImpl extends AbstractCrudService<Maintenance, Long> implements MaintenanceService {

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        super(maintenanceRepository, "Maintenance");
    }

    @Override
    protected void assignId(Maintenance maintenance, Long id) {
        maintenance.setIdMaintenance(id);
    }

    @Override
    protected void validate(Maintenance maintenance) {
        if (maintenance.getDateFin() != null && maintenance.getDateDebut() != null
                && maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new BadRequestException("La date de fin ne peut pas précéder la date de début");
        }
    }
}
