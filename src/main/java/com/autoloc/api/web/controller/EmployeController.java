package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Employe;
import com.autoloc.api.service.EmployeService;
import io.swagger.v3.oas.annotations.Operation;
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
    public List<Employe> getAll() {
        return employeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Employé par identifiant")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    public Employe getById(@PathVariable Long id) {
        return employeService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Employé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    public Employe create(@RequestBody Employe employe) {
        return employeService.create(employe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Employé")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    public Employe update(@PathVariable Long id, @RequestBody Employe employe) {
        return employeService.update(id, employe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Employé")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    public void delete(@PathVariable Long id) {
        employeService.delete(id);
    }

    @PutMapping("/{idEmploye}/affecter/{idAgence}")
    @Operation(summary = "Affecter un employé à une agence",
            description = "L'employé ne doit appartenir à aucune agence : en cas de transfert, le désaffecter d'abord.")
    @ApiResponse(responseCode = "400", description = "Employé déjà affecté à une agence")
    @ApiResponse(responseCode = "404", description = "Employé ou agence introuvable")
    public Employe affecterEmploye(@PathVariable Long idEmploye, @PathVariable Long idAgence) {
        return employeService.affecterEmploye(idEmploye, idAgence);
    }

    @PutMapping("/{idEmploye}/desaffecter")
    @Operation(summary = "Désaffecter un employé de son agence")
    @ApiResponse(responseCode = "400", description = "Employé déjà sans agence")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    public Employe desaffecterEmploye(@PathVariable Long idEmploye) {
        return employeService.desaffecterEmploye(idEmploye);
    }
}
