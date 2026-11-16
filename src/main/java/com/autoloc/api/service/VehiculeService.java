package com.autoloc.api.service;

import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.dto.TarifResponse;

public interface VehiculeService extends CrudService<Vehicule, Long> {

    /** Rattache le véhicule à une agence. */
    Vehicule affecterVehicule(Long idVehicule, Long idAgence);

    /** Installe un équipement sur le véhicule. */
    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);

    /** Retire un équipement du véhicule. */
    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);

    /** Calcule le prix d'une location selon une politique tarifaire (patron Strategy). */
    TarifResponse calculerTarif(Long idVehicule, int nombreJours, String typeTarif);
}
