package com.autoloc.api.web.controller;

import com.autoloc.api.domain.Employe;
import com.autoloc.api.repository.EmployeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final EmployeRepository employeRepository;

    public EmployeController(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @GetMapping
    public List<Employe> getAll() {
        return employeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employe getById(@PathVariable Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé introuvable"));
    }

    @PostMapping
    public Employe create(@RequestBody Employe employe) {
        return employeRepository.save(employe);
    }

    @PutMapping("/{id}")
    public Employe update(@PathVariable Long id, @RequestBody Employe employe) {
        employe.setIdEmploye(id);
        return employeRepository.save(employe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeRepository.deleteById(id);
    }
}
