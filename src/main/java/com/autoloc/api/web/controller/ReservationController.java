package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Reservation;
import com.autoloc.api.dto.ReservationDTO;
import com.autoloc.api.exception.ErrorResponse;
import com.autoloc.api.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservation", description = "Gestion des réservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Operation(summary = "Lister : Réservation")
    public List<ReservationDTO> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Réservation par identifiant")
    @ApiResponse(responseCode = "200", description = "Réservation trouvé(e)")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ReservationDTO getById(@PathVariable Long id) {
        return reservationService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Réservation")
    @ApiResponse(responseCode = "200", description = "Réservation créé(e)")
    @ApiResponse(responseCode = "400", description = "Données invalides",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ReservationDTO create(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Réservation")
    @ApiResponse(responseCode = "200", description = "Réservation modifié(e)")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ReservationDTO update(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.update(id, reservation);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Réservation")
    @ApiResponse(responseCode = "200", description = "Réservation supprimé(e)")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public void delete(@PathVariable Long id) {
        reservationService.delete(id);
    }
}
