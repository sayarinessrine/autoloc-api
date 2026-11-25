package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Contrat;
import com.autoloc.api.dto.ContratDTO;
import com.autoloc.api.exception.ErrorResponse;
import com.autoloc.api.service.ContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    public List<ContratDTO> getAll() {
        return contratService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Contrat par identifiant")
    @ApiResponse(responseCode = "200", description = "Contrat trouvé(e)")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ContratDTO getById(@PathVariable Long id) {
        return contratService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Contrat")
    @ApiResponse(responseCode = "200", description = "Contrat créé(e)")
    @ApiResponse(responseCode = "400", description = "Données invalides",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ContratDTO create(@RequestBody Contrat contrat) {
        return contratService.create(contrat);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Contrat")
    @ApiResponse(responseCode = "200", description = "Contrat modifié(e)")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ContratDTO update(@PathVariable Long id, @RequestBody Contrat contrat) {
        return contratService.update(id, contrat);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Contrat")
    @ApiResponse(responseCode = "200", description = "Contrat supprimé(e)")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public void delete(@PathVariable Long id) {
        contratService.delete(id);
    }
}
