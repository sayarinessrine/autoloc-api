package com.autoloc.api.service.tarification;

import org.springframework.stereotype.Component;

/** Tarif longue durée : réduction de 20 %. */
@Component("LONGUE_DUREE")
public class TarificationLongueDuree implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.80;
    }
}
