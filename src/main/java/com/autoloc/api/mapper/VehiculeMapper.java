package com.autoloc.api.mapper;

import com.autoloc.api.domain.Vehicule;
import com.autoloc.api.dto.VehiculeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EquipementMapper.class)
public interface VehiculeMapper extends EntityMapper<Vehicule, VehiculeDTO> {

    @Override
    @Mapping(target = "idAgence", source = "agence.idAgence")
    @Mapping(target = "nomAgence", source = "agence.nom")
    VehiculeDTO toDto(Vehicule vehicule);
}
