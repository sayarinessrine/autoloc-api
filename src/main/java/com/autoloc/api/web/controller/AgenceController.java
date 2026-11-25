package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.dto.AgenceDTO;
import com.autoloc.api.exception.ErrorResponse;
import com.autoloc.api.service.AgenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    public List<AgenceDTO> getAll() {
        return agenceService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Agence par identifiant")
    @ApiResponse(responseCode = "200", description = "Agence trouvé(e)")
    @ApiResponse(responseCode = "404", description = "Agence introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public AgenceDTO getById(@PathVariable Long id) {
        return agenceService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Agence")
    @ApiResponse(responseCode = "200", description = "Agence créé(e)")
    @ApiResponse(responseCode = "400", description = "Données invalides",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public AgenceDTO create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Agence")
    @ApiResponse(responseCode = "200", description = "Agence modifié(e)")
    @ApiResponse(responseCode = "404", description = "Agence introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public AgenceDTO update(@PathVariable Long id, @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Agence")
    @ApiResponse(responseCode = "200", description = "Agence supprimé(e)")
    @ApiResponse(responseCode = "404", description = "Agence introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public void delete(@PathVariable Long id) {
        agenceService.delete(id);
    }
}
