package com.autoloc.api.dto;

import com.autoloc.api.domain.CategorieVehicule;
import com.autoloc.api.domain.StatutVehicule;
import java.math.BigDecimal;
import java.util.List;

/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record VehiculeDTO(
        Long idVehicule, String immatriculation, String marque, String modele,
        CategorieVehicule categorie, BigDecimal tarifJournalier, StatutVehicule statut,
        Long idAgence, String nomAgence, List<EquipementDTO> equipements) {
}
