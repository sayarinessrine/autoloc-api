package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.domain.Equipement;
import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.dto.TarifResponse;
import com.autoloc.api.dto.VehiculeDTO;
import com.autoloc.api.exception.BadRequestException;
import com.autoloc.api.mapper.VehiculeMapper;
import com.autoloc.api.repository.VehiculeRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.AgenceService;
import com.autoloc.api.service.EquipementService;
import com.autoloc.api.service.JournalisationService;
import com.autoloc.api.service.VehiculeService;
import com.autoloc.api.service.tarification.TarificationService;
import com.autoloc.api.validation.VehiculeValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * SRP : ce service orchestre la gestion des véhicules ; la validation, la journalisation,
 * le calcul du tarif et la conversion en DTO sont délégués à des classes dédiées.
 */
@Service
public class VehiculeServiceImpl extends AbstractCrudService<Vehicule, VehiculeDTO, Long> implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceService agenceService;
    private final EquipementService equipementService;
    private final VehiculeValidator vehiculeValidator;
    private final JournalisationService journalisationService;
    private final TarificationService tarificationService;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository,
                               VehiculeMapper vehiculeMapper,
                               AgenceService agenceService,
                               EquipementService equipementService,
                               VehiculeValidator vehiculeValidator,
                               JournalisationService journalisationService,
                               TarificationService tarificationService) {
        super(vehiculeRepository, vehiculeMapper, "Véhicule");
        this.vehiculeRepository = vehiculeRepository;
        this.agenceService = agenceService;
        this.equipementService = equipementService;
        this.vehiculeValidator = vehiculeValidator;
        this.journalisationService = journalisationService;
        this.tarificationService = tarificationService;
    }

    @Override
    protected void assignId(Vehicule vehicule, Long id) {
        vehicule.setIdVehicule(id);
    }

    @Override
    protected void validate(Vehicule vehicule) {
        vehiculeValidator.valider(vehicule);
    }

    @Override
    @Transactional
    public VehiculeDTO create(Vehicule vehicule) {
        VehiculeDTO enregistre = super.create(vehicule);
        journalisationService.info("Ajout du véhicule id={} immatriculation={}",
                enregistre.idVehicule(), enregistre.immatriculation());
        return enregistre;
    }

    @Override
    public TarifResponse calculerTarif(Long idVehicule, int nombreJours, String typeTarif) {
        vehiculeValidator.validerNombreJours(nombreJours);
        Vehicule vehicule = findEntityById(idVehicule);
        double tarifJournalier = vehicule.getTarifJournalier().doubleValue();
        double prixTotal = tarificationService.calculer(typeTarif, tarifJournalier, nombreJours);
        journalisationService.info("Calcul du tarif véhicule={} type={} jours={} prix={}",
                idVehicule, typeTarif, nombreJours, prixTotal);
        return new TarifResponse(vehicule.getIdVehicule(), vehicule.getImmatriculation(),
                typeTarif.trim().toUpperCase(), nombreJours, tarifJournalier, prixTotal);
    }

    @Override
    @Transactional
    public VehiculeDTO affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = findEntityById(idVehicule);
        Agence agence = agenceService.findEntityById(idAgence);
        vehicule.setAgence(agence);
        return toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    @Transactional
    public VehiculeDTO affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findEntityById(idVehicule);
        Equipement equipement = equipementService.findEntityById(idEquipement);
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
        return toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    @Transactional
    public VehiculeDTO desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findEntityById(idVehicule);
        equipementService.findEntityById(idEquipement);
        boolean retire = vehicule.getEquipements() != null
                && vehicule.getEquipements().removeIf(e -> e.getIdEquipement().equals(idEquipement));
        if (!retire) {
            throw new BadRequestException("L'équipement " + idEquipement
                    + " n'est pas installé sur le véhicule " + idVehicule);
        }
        return toDto(vehiculeRepository.save(vehicule));
    }
}
