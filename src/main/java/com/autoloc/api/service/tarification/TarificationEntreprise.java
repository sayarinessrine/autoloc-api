package com.autoloc.api.service.tarification;

import org.springframework.stereotype.Component;

/** Tarif entreprise : réduction de 15 %. Ajouté sans modifier TarificationService (OCP). */
@Component("ENTREPRISE")
public class TarificationEntreprise implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.85;
    }
}
