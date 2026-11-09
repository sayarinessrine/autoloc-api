package com.autoloc.api.service;

import com.autoloc.api.domain.Vehicule;

public interface VehiculeService extends CrudService<Vehicule, Long> {

    /** Rattache le véhicule à une agence. */
    Vehicule affecterVehicule(Long idVehicule, Long idAgence);

    /** Installe un équipement sur le véhicule. */
    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);

    /** Retire un équipement du véhicule. */
    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);
}
