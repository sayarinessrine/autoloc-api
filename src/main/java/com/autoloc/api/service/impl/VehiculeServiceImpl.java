package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.domain.Equipement;
import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.exception.BadRequestException;
import com.autoloc.api.repository.VehiculeRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.AgenceService;
import com.autoloc.api.service.EquipementService;
import com.autoloc.api.service.VehiculeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class VehiculeServiceImpl extends AbstractCrudService<Vehicule, Long> implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceService agenceService;
    private final EquipementService equipementService;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository,
                               AgenceService agenceService,
                               EquipementService equipementService) {
        super(vehiculeRepository, "Véhicule");
        this.vehiculeRepository = vehiculeRepository;
        this.agenceService = agenceService;
        this.equipementService = equipementService;
    }

    @Override
    protected void assignId(Vehicule vehicule, Long id) {
        vehicule.setIdVehicule(id);
    }

    @Override
    protected void validate(Vehicule vehicule) {
        if (vehicule.getTarifJournalier() == null
                || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Le tarif journalier doit être strictement positif");
        }
    }

    @Override
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = getById(idVehicule);
        Agence agence = agenceService.getById(idAgence);
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = getById(idVehicule);
        Equipement equipement = equipementService.getById(idEquipement);
        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new ArrayList<>());
        }
        boolean dejaInstalle = vehicule.getEquipements().stream()
                .anyMatch(e -> e.getIdEquipement().equals(idEquipement));
        if (dejaInstalle) {
            throw new BadRequestException("L'équipement " + idEquipement
                    + " est déjà installé sur le véhicule " + idVehicule);
        }
        vehicule.getEquipements().add(equipement);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = getById(idVehicule);
        equipementService.getById(idEquipement);
        boolean retire = vehicule.getEquipements() != null
                && vehicule.getEquipements().removeIf(e -> e.getIdEquipement().equals(idEquipement));
        if (!retire) {
            throw new BadRequestException("L'équipement " + idEquipement
                    + " n'est pas installé sur le véhicule " + idVehicule);
        }
        return vehiculeRepository.save(vehicule);
    }
}
