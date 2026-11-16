package com.autoloc.api.validation;

import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * SRP : cette classe n'a qu'une raison de changer : les règles de validation métier d'un véhicule.
 */
@Component
public class VehiculeValidator {

    public void valider(Vehicule vehicule) {
        if (vehicule.getTarifJournalier() == null
                || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Le tarif journalier doit être strictement positif");
        }
    }

    public void validerNombreJours(int nombreJours) {
        if (nombreJours <= 0) {
            throw new BadRequestException("Le nombre de jours doit être positif");
        }
    }
}
