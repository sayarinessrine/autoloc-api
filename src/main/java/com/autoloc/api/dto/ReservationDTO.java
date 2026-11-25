package com.autoloc.api.dto;

import com.autoloc.api.domain.StatutReservation;
import java.time.LocalDate;

/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record ReservationDTO(
        Long idReservation, LocalDate dateDebut, LocalDate dateFin, StatutReservation statut,
        Long idVehicule, String immatriculation, Long idClient, String nomClient, String prenomClient) {
}
