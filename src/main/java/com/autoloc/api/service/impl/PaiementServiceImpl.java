package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Paiement;
import com.autoloc.api.exception.BadRequestException;
import com.autoloc.api.repository.PaiementRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.PaiementService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class PaiementServiceImpl extends AbstractCrudService<Paiement, Long> implements PaiementService {

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        super(paiementRepository, "Paiement");
    }

    @Override
    protected void assignId(Paiement paiement, Long id) {
        paiement.setIdPaiement(id);
    }

    @Override
    protected void validate(Paiement paiement) {
        if (paiement.getMontant() == null
                || paiement.getMontant().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Le montant du paiement doit être strictement positif");
        }
    }
}
