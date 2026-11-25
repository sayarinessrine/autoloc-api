package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Client;
import com.autoloc.api.dto.ClientDTO;
import com.autoloc.api.exception.ErrorResponse;
import com.autoloc.api.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    public List<ClientDTO> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter : Client par identifiant")
    @ApiResponse(responseCode = "200", description = "Client trouvé(e)")
    @ApiResponse(responseCode = "404", description = "Client introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ClientDTO getById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer : Client")
    @ApiResponse(responseCode = "200", description = "Client créé(e)")
    @ApiResponse(responseCode = "400", description = "Données invalides",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Doublon sur un champ unique",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ClientDTO create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier : Client")
    @ApiResponse(responseCode = "200", description = "Client modifié(e)")
    @ApiResponse(responseCode = "404", description = "Client introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ClientDTO update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer : Client")
    @ApiResponse(responseCode = "200", description = "Client supprimé(e)")
    @ApiResponse(responseCode = "404", description = "Client introuvable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Élément encore référencé ailleurs",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
