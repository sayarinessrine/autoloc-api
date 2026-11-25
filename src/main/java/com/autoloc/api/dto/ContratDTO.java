package com.autoloc.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record ContratDTO(
        Long idContrat, LocalDate dateSignature, BigDecimal montantTotal, boolean valide, Long idReservation) {
}
