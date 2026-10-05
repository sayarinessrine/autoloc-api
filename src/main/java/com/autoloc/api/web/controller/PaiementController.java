package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Paiement;
import com.autoloc.api.repository.PaiementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementRepository paiementRepository;

    public PaiementController(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @GetMapping
    public List<Paiement> getAll() {
        return paiementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Paiement getById(@PathVariable Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
    }

    @PostMapping
    public Paiement create(@RequestBody Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @PutMapping("/{id}")
    public Paiement update(@PathVariable Long id, @RequestBody Paiement paiement) {
        paiement.setIdPaiement(id);
        return paiementRepository.save(paiement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paiementRepository.deleteById(id);
    }
}
