package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Paiement;
import com.autoloc.api.service.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Paiement", description = "Gestion des paiements")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @GetMapping
    @Operation(summary = "Lister : Paiement")
    public List<Paiement> getAll() {
        return paiementService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Paiement par identifiant")
    @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    public Paiement getById(@PathVariable Long id) {
        return paiementService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Paiement")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique")
    public Paiement create(@RequestBody Paiement paiement) {
        return paiementService.create(paiement);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Paiement")
    @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    public Paiement update(@PathVariable Long id, @RequestBody Paiement paiement) {
        return paiementService.update(id, paiement);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Paiement")
    @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs")
    public void delete(@PathVariable Long id) {
        paiementService.delete(id);
    }
}
