package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Contrat;
import com.autoloc.api.exception.BadRequestException;
import com.autoloc.api.repository.ContratRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.ContratService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ContratServiceImpl extends AbstractCrudService<Contrat, Long> implements ContratService {

    public ContratServiceImpl(ContratRepository contratRepository) {
        super(contratRepository, "Contrat");
    }

    @Override
    protected void assignId(Contrat contrat, Long id) {
        contrat.setIdContrat(id);
    }

    @Override
    protected void validate(Contrat contrat) {
        if (contrat.getMontantTotal() == null
                || contrat.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Le montant total ne peut pas être négatif");
        }
    }
}
