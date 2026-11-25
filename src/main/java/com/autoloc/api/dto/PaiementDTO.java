package com.autoloc.api.dto;

import com.autoloc.api.domain.ModePaiement;
import java.math.BigDecimal;
import java.time.LocalDate;

/** DTO de réponse : ne contient que les champs utiles à l'API. */
public record PaiementDTO(
        Long idPaiement, BigDecimal montant, LocalDate datePaiement, ModePaiement modePaiement, Long idContrat) {
}
