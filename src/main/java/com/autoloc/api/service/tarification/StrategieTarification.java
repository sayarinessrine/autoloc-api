package com.autoloc.api.service.tarification;

/** Patron GoF Strategy : une famille d'algorithmes de calcul de prix interchangeables. */
public interface StrategieTarification {

    double calculer(double tarifJournalier, int jours);
}
