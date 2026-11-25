package com.autoloc.api.dto;


/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record AgenceDTO(
        Long idAgence, String nom, String ville, String adresse, String telephone) {
}
