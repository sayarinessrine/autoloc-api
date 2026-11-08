package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Client;
import com.autoloc.api.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Client", description = "Gestion des clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Lister : Client")
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Client par identifiant")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    public Client getById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Client")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique")
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Client")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Client")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
