package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.service.AgenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@Tag(name = "Agence", description = "Gestion des agences")
public class AgenceController {

    private final AgenceService agenceService;

    public AgenceController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @GetMapping
    @Operation(summary = "Lister : Agence")
    public List<Agence> getAll() {
        return agenceService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Agence par identifiant")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    public Agence getById(@PathVariable Long id) {
        return agenceService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Agence")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique")
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Agence")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    public Agence update(@PathVariable Long id, @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Agence")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs")
    public void delete(@PathVariable Long id) {
        agenceService.delete(id);
    }
}
