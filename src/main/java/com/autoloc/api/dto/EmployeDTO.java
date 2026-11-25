package com.autoloc.api.dto;

import com.autoloc.api.domain.RoleEmploye;

/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record EmployeDTO(
        Long idEmploye, String nom, String prenom, RoleEmploye role, Long idAgence, String nomAgence) {
}
