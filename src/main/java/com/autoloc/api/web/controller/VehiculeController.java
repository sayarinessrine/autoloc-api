package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.dto.TarifResponse;
import com.autoloc.api.exception.ErrorResponse;
import com.autoloc.api.service.VehiculeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@Tag(name = "Vehicule", description = "Gestion des véhicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    @Operation(summary = "Lister : Véhicule")
    public List<Vehicule> getAll() {
        return vehiculeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Véhicule par identifiant")
    @ApiResponse(responseCode = "200", description = "Véhicule trouvé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Vehicule getById(@PathVariable Long id) {
        return vehiculeService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Véhicule")
    @ApiResponse(responseCode = "200", description = "Véhicule créé")
    @ApiResponse(responseCode = "400", description = "Données invalides",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Immatriculation déjà existante",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Véhicule")
    @ApiResponse(responseCode = "200", description = "Véhicule modifié")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Véhicule")
    @ApiResponse(responseCode = "200", description = "Véhicule supprimé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Véhicule encore référencé ailleurs",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public void delete(@PathVariable Long id) {
        vehiculeService.delete(id);
    }

    @PutMapping("/{idVehicule}/affecter/{idAgence}")
    @Operation(summary = "Affecter un véhicule à une agence")
    @ApiResponse(responseCode = "200", description = "Véhicule affecté")
    @ApiResponse(responseCode = "404", description = "Véhicule ou agence introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Vehicule affecterVehicule(@PathVariable Long idVehicule, @PathVariable Long idAgence) {
        return vehiculeService.affecterVehicule(idVehicule, idAgence);
    }

    @PutMapping("/{idVehicule}/affecter-equipement/{idEquipement}")
    @Operation(summary = "Affecter un équipement à un véhicule")
    @ApiResponse(responseCode = "200", description = "Équipement affecté")
    @ApiResponse(responseCode = "400", description = "Équipement déjà installé sur ce véhicule",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Véhicule ou équipement introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Vehicule affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }

    @PutMapping("/{idVehicule}/desaffecter-equipement/{idEquipement}")
    @Operation(summary = "Désaffecter un équipement d'un véhicule")
    @ApiResponse(responseCode = "200", description = "Équipement désaffecté")
    @ApiResponse(responseCode = "400", description = "Équipement non installé sur ce véhicule",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Véhicule ou équipement introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Vehicule desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
    }

    @GetMapping("/{idVehicule}/tarif")
    @Operation(summary = "Calculer le prix d'une location (patron Strategy)",
            description = "Types possibles : voir GET /api/tarifs/types (STANDARD, WEEKEND, LONGUE_DUREE...)")
    @ApiResponse(responseCode = "200", description = "Prix calculé")
    @ApiResponse(responseCode = "400", description = "Nombre de jours invalide ou type de tarif inconnu",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public TarifResponse calculerTarif(
            @PathVariable Long idVehicule,
            @Parameter(description = "Nombre de jours de location") @RequestParam int jours,
            @Parameter(description = "Politique tarifaire") @RequestParam(defaultValue = "STANDARD") String type) {
        return vehiculeService.calculerTarif(idVehicule, jours, type);
    }
}