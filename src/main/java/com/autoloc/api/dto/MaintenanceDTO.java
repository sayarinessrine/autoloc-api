package com.autoloc.api.dto;

import java.time.LocalDate;

/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record MaintenanceDTO(
        Long idMaintenance, LocalDate dateDebut, LocalDate dateFin, String description,
        Long idVehicule, String immatriculation) {
}
