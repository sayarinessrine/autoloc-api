package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.dto.AgenceDTO;
import com.autoloc.api.mapper.AgenceMapper;
import com.autoloc.api.repository.AgenceRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.AgenceService;
import org.springframework.stereotype.Service;

@Service
public class AgenceServiceImpl extends AbstractCrudService<Agence, AgenceDTO, Long> implements AgenceService {

    public AgenceServiceImpl(AgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        super(agenceRepository, agenceMapper, "Agence");
    }

    @Override
    protected void assignId(Agence agence, Long id) {
        agence.setIdAgence(id);
    }
}
