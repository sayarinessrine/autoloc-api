package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Contrat;
import com.autoloc.api.repository.ContratRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    private final ContratRepository contratRepository;

    public ContratController(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @GetMapping
    public List<Contrat> getAll() {
        return contratRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contrat getById(@PathVariable Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable"));
    }

    @PostMapping
    public Contrat create(@RequestBody Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @PutMapping("/{id}")
    public Contrat update(@PathVariable Long id, @RequestBody Contrat contrat) {
        contrat.setIdContrat(id);
        return contratRepository.save(contrat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contratRepository.deleteById(id);
    }
}
