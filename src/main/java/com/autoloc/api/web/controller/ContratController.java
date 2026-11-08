package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Contrat;
import com.autoloc.api.service.ContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrat", description = "Gestion des contrats")
public class ContratController {

    private final ContratService contratService;

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    @GetMapping
    @Operation(summary = "Lister : Contrat")
    public List<Contrat> getAll() {
        return contratService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Contrat par identifiant")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    public Contrat getById(@PathVariable Long id) {
        return contratService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Contrat")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique")
    public Contrat create(@RequestBody Contrat contrat) {
        return contratService.create(contrat);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Contrat")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    public Contrat update(@PathVariable Long id, @RequestBody Contrat contrat) {
        return contratService.update(id, contrat);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Contrat")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs")
    public void delete(@PathVariable Long id) {
        contratService.delete(id);
    }
}
