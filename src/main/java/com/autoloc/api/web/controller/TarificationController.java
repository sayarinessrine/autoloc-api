package com.autoloc.api.web.controller;

import com.autoloc.api.service.tarification.TarificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tarifs")
@Tag(name = "Tarification", description = "Politiques tarifaires (patron Strategy)")
public class TarificationController {

    private final TarificationService tarificationService;

    public TarificationController(TarificationService tarificationService) {
        this.tarificationService = tarificationService;
    }

    @GetMapping("/types")
    @Operation(summary = "Lister les politiques tarifaires disponibles")
    public List<String> types() {
        return tarificationService.typesDisponibles();
    }
}
