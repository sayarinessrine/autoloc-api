package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.domain.Employe;
import com.autoloc.api.dto.EmployeDTO;
import com.autoloc.api.exception.BadRequestException;
import com.autoloc.api.mapper.EmployeMapper;
import com.autoloc.api.repository.EmployeRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.AgenceService;
import com.autoloc.api.service.EmployeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeServiceImpl extends AbstractCrudService<Employe, EmployeDTO, Long> implements EmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceService agenceService;

    public EmployeServiceImpl(EmployeRepository employeRepository,
                              EmployeMapper employeMapper,
                              AgenceService agenceService) {
        super(employeRepository, employeMapper, "Employé");
        this.employeRepository = employeRepository;
        this.agenceService = agenceService;
    }

    @Override
    protected void assignId(Employe employe, Long id) {
        employe.setIdEmploye(id);
    }

    @Override
    @Transactional
    public EmployeDTO desaffecterEmploye(Long idEmploye) {
        Employe employe = findEntityById(idEmploye);
        if (employe.getAgence() == null) {
            throw new BadRequestException("L'employé " + idEmploye + " n'est affecté à aucune agence");
        }
        employe.setAgence(null);
        return toDto(employeRepository.save(employe));
    }

    @Override
    @Transactional
    public EmployeDTO affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = findEntityById(idEmploye);
        Agence agence = agenceService.findEntityById(idAgence);
        if (employe.getAgence() != null) {
            throw new BadRequestException("L'employé " + idEmploye
                    + " est déjà affecté à une agence : il faut d'abord le désaffecter avant de le transférer");
        }
        employe.setAgence(agence);
        return toDto(employeRepository.save(employe));
    }
}
