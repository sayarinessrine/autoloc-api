package com.autoloc.api.service;

import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.dto.TarifResponse;
import com.autoloc.api.dto.VehiculeDTO;

public interface VehiculeService extends CrudService<Vehicule, VehiculeDTO, Long> {

    /** Rattache le véhicule à une agence. */
    VehiculeDTO affecterVehicule(Long idVehicule, Long idAgence);

    /** Installe un équipement sur le véhicule. */
    VehiculeDTO affecterEquipement(Long idVehicule, Long idEquipement);

    /** Retire un équipement du véhicule. */
    VehiculeDTO desaffecterEquipement(Long idVehicule, Long idEquipement);

    /** Calcule le prix d'une location selon une politique tarifaire (patron Strategy). */
    TarifResponse calculerTarif(Long idVehicule, int nombreJours, String typeTarif);
}
