package com.autoloc.api.dto;


/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record ClientDTO(
        Long idClient, String nom, String prenom, String email, String telephone, String dateInscription) {
}
