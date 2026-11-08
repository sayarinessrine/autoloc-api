package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Equipement;
import com.autoloc.api.service.EquipementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@Tag(name = "Equipement", description = "Gestion des équipements")
public class EquipementController {

    private final EquipementService equipementService;

    public EquipementController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

    @GetMapping
    @Operation(summary = "Lister : Équipement")
    public List<Equipement> getAll() {
        return equipementService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Équipement par identifiant")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    public Equipement getById(@PathVariable Long id) {
        return equipementService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Équipement")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique")
    public Equipement create(@RequestBody Equipement equipement) {
        return equipementService.create(equipement);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Équipement")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement equipement) {
        return equipementService.update(id, equipement);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Équipement")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs")
    public void delete(@PathVariable Long id) {
        equipementService.delete(id);
    }
}
