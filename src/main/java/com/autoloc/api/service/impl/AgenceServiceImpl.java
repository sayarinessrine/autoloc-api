package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.repository.AgenceRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.AgenceService;
import org.springframework.stereotype.Service;

@Service
public class AgenceServiceImpl extends AbstractCrudService<Agence, Long> implements AgenceService {

    public AgenceServiceImpl(AgenceRepository agenceRepository) {
        super(agenceRepository, "Agence");
    }

    @Override
    protected void assignId(Agence agence, Long id) {
        agence.setIdAgence(id);
    }
}
