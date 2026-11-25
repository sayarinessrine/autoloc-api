package com.autoloc.api.dto;


/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record EquipementDTO(
        Long idEquipement, String libelle) {
}
