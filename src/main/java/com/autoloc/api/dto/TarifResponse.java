package com.autoloc.api.dto;

public record TarifResponse(
        Long idVehicule,
        String immatriculation,
        String typeTarif,
        int nombreJours,
        double tarifJournalier,
        double prixTotal) {
}
