package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Employe;
import com.autoloc.api.repository.EmployeRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.EmployeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeServiceImpl extends AbstractCrudService<Employe, Long> implements EmployeService {

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        super(employeRepository, "Employé");
    }

    @Override
    protected void assignId(Employe employe, Long id) {
        employe.setIdEmploye(id);
    }
}
