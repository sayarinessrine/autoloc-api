package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Equipement;
import com.autoloc.api.repository.EquipementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

    private final EquipementRepository equipementRepository;

    public EquipementController(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @GetMapping
    public List<Equipement> getAll() {
        return equipementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Equipement getById(@PathVariable Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Équipement introuvable"));
    }

    @PostMapping
    public Equipement create(@RequestBody Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @PutMapping("/{id}")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement equipement) {
        equipement.setIdEquipement(id);
        return equipementRepository.save(equipement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        equipementRepository.deleteById(id);
    }
}
