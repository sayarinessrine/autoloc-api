package com.autoloc.api.mapper;

import com.autoloc.api.domain.Maintenance;
import com.autoloc.api.dto.MaintenanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper extends EntityMapper<Maintenance, MaintenanceDTO> {

    @Override
    @Mapping(target = "idVehicule", source = "vehicule.idVehicule")
    @Mapping(target = "immatriculation", source = "vehicule.immatriculation")
    MaintenanceDTO toDto(Maintenance maintenance);
}
