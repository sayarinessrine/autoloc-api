package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Maintenance;
import com.autoloc.api.service.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@Tag(name = "Maintenance", description = "Gestion des maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    @Operation(summary = "Lister : Maintenance")
    public List<Maintenance> getAll() {
        return maintenanceService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Maintenance par identifiant")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    public Maintenance getById(@PathVariable Long id) {
        return maintenanceService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Maintenance")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique")
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Maintenance")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Maintenance")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs")
    public void delete(@PathVariable Long id) {
        maintenanceService.delete(id);
    }
}
