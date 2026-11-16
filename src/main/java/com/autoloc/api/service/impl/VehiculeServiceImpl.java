package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.domain.Equipement;
import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.dto.TarifResponse;
import com.autoloc.api.exception.BadRequestException;
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
 * SRP : ce service ne fait que ORCHESTRER la gestion des véhicules.
 * La validation, la journalisation et le calcul du tarif sont délégués à des classes dédiées.
 * DIP : toutes les dépendances sont des abstractions injectées par Spring (constructeur).
 */
@Service
public class VehiculeServiceImpl extends AbstractCrudService<Vehicule, Long> implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceService agenceService;
    private final EquipementService equipementService;
    private final VehiculeValidator vehiculeValidator;
    private final JournalisationService journalisationService;
    private final TarificationService tarificationService;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository,
                               AgenceService agenceService,
                               EquipementService equipementService,
                               VehiculeValidator vehiculeValidator,
                               JournalisationService journalisationService,
                               TarificationService tarificationService) {
        super(vehiculeRepository, "Véhicule");
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

    /** La validation est déléguée au validateur (point d'extension de AbstractCrudService). */
    @Override
    protected void validate(Vehicule vehicule) {
        vehiculeValidator.valider(vehicule);
    }

    /** Ajout d'un véhicule : validation (via validate), sauvegarde, puis journalisation. */
    @Override
    @Transactional
    public Vehicule create(Vehicule vehicule) {
        Vehicule enregistre = super.create(vehicule);
        journalisationService.info("Ajout du véhicule id={} immatriculation={}",
                enregistre.getIdVehicule(), enregistre.getImmatriculation());
        return enregistre;
    }

    @Override
    public TarifResponse calculerTarif(Long idVehicule, int nombreJours, String typeTarif) {
        vehiculeValidator.validerNombreJours(nombreJours);
        Vehicule vehicule = getById(idVehicule);
        double tarifJournalier = vehicule.getTarifJournalier().doubleValue();
        double prixTotal = tarificationService.calculer(typeTarif, tarifJournalier, nombreJours);
        journalisationService.info("Calcul du tarif véhicule={} type={} jours={} prix={}",
                idVehicule, typeTarif, nombreJours, prixTotal);
        return new TarifResponse(vehicule.getIdVehicule(), vehicule.getImmatriculation(),
                typeTarif.trim().toUpperCase(), nombreJours, tarifJournalier, prixTotal);
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
