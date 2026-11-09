package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.service.VehiculeService;
import io.swagger.v3.oas.annotations.Operation;
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
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    public Vehicule getById(@PathVariable Long id) {
        return vehiculeService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Véhicule")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Immatriculation déjà existante")
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Véhicule")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Véhicule")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @ApiResponse(responseCode = "409", description = "Véhicule encore référencé ailleurs")
    public void delete(@PathVariable Long id) {
        vehiculeService.delete(id);
    }

    @PutMapping("/{idVehicule}/affecter/{idAgence}")
    @Operation(summary = "Affecter un véhicule à une agence")
    @ApiResponse(responseCode = "404", description = "Véhicule ou agence introuvable")
    public Vehicule affecterVehicule(@PathVariable Long idVehicule, @PathVariable Long idAgence) {
        return vehiculeService.affecterVehicule(idVehicule, idAgence);
    }

    @PutMapping("/{idVehicule}/affecter-equipement/{idEquipement}")
    @Operation(summary = "Affecter un équipement à un véhicule")
    @ApiResponse(responseCode = "400", description = "Équipement déjà installé sur ce véhicule")
    @ApiResponse(responseCode = "404", description = "Véhicule ou équipement introuvable")
    public Vehicule affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }

    @PutMapping("/{idVehicule}/desaffecter-equipement/{idEquipement}")
    @Operation(summary = "Désaffecter un équipement d'un véhicule")
    @ApiResponse(responseCode = "400", description = "Équipement non installé sur ce véhicule")
    @ApiResponse(responseCode = "404", description = "Véhicule ou équipement introuvable")
    public Vehicule desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
    }
}
