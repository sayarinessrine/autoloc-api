package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Equipement;
import com.autoloc.api.dto.EquipementDTO;
import com.autoloc.api.exception.ErrorResponse;
import com.autoloc.api.service.EquipementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    public List<EquipementDTO> getAll() {
        return equipementService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Équipement par identifiant")
    @ApiResponse(responseCode = "200", description = "Équipement trouvé(e)")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public EquipementDTO getById(@PathVariable Long id) {
        return equipementService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Équipement")
    @ApiResponse(responseCode = "200", description = "Équipement créé(e)")
    @ApiResponse(responseCode = "400", description = "Données invalides",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public EquipementDTO create(@RequestBody Equipement equipement) {
        return equipementService.create(equipement);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Équipement")
    @ApiResponse(responseCode = "200", description = "Équipement modifié(e)")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public EquipementDTO update(@PathVariable Long id, @RequestBody Equipement equipement) {
        return equipementService.update(id, equipement);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Équipement")
    @ApiResponse(responseCode = "200", description = "Équipement supprimé(e)")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public void delete(@PathVariable Long id) {
        equipementService.delete(id);
    }
}
