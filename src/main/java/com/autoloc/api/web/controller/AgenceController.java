package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.repository.AgenceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final AgenceRepository agenceRepository;

    public AgenceController(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @GetMapping
    public List<Agence> getAll() {
        return agenceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Agence getById(@PathVariable Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence introuvable"));
    }

    @PostMapping
    public Agence create(@RequestBody Agence agence) {
        return agenceRepository.save(agence);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        agenceRepository.deleteById(id);
    }
}