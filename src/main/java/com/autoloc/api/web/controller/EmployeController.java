package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Employe;
import com.autoloc.api.dto.EmployeDTO;
import com.autoloc.api.exception.ErrorResponse;
import com.autoloc.api.service.EmployeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@Tag(name = "Employe", description = "Gestion des employés")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    @Operation(summary = "Lister : Employé")
    public List<EmployeDTO> getAll() {
        return employeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Employé par identifiant")
    @ApiResponse(responseCode = "200", description = "Employé trouvé(e)")
    @ApiResponse(responseCode = "404", description = "Employé introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public EmployeDTO getById(@PathVariable Long id) {
        return employeService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Employé")
    @ApiResponse(responseCode = "200", description = "Employé créé(e)")
    @ApiResponse(responseCode = "400", description = "Données invalides",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public EmployeDTO create(@RequestBody Employe employe) {
        return employeService.create(employe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Employé")
    @ApiResponse(responseCode = "200", description = "Employé modifié(e)")
    @ApiResponse(responseCode = "404", description = "Employé introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public EmployeDTO update(@PathVariable Long id, @RequestBody Employe employe) {
        return employeService.update(id, employe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Employé")
    @ApiResponse(responseCode = "200", description = "Employé supprimé(e)")
    @ApiResponse(responseCode = "404", description = "Employé introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public void delete(@PathVariable Long id) {
        employeService.delete(id);
    }

    @PutMapping("/{idEmploye}/affecter/{idAgence}")
    @Operation(summary = "Affecter un employé à une agence",
            description = "L'employé ne doit appartenir à aucune agence : en cas de transfert, le désaffecter d'abord.")
    @ApiResponse(responseCode = "200", description = "Employé affecté")
    @ApiResponse(responseCode = "400", description = "Employé déjà affecté à une agence",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Employé ou agence introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public EmployeDTO affecterEmploye(@PathVariable Long idEmploye, @PathVariable Long idAgence) {
        return employeService.affecterEmploye(idEmploye, idAgence);
    }

    @PutMapping("/{idEmploye}/desaffecter")
    @Operation(summary = "Désaffecter un employé de son agence")
    @ApiResponse(responseCode = "200", description = "Employé désaffecté")
    @ApiResponse(responseCode = "400", description = "Employé déjà sans agence",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Employé introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public EmployeDTO desaffecterEmploye(@PathVariable Long idEmploye) {
        return employeService.desaffecterEmploye(idEmploye);
    }
}
