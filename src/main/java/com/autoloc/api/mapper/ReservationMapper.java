package com.autoloc.api.mapper;

import com.autoloc.api.domain.Reservation;
import com.autoloc.api.dto.ReservationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends EntityMapper<Reservation, ReservationDTO> {

    @Override
    @Mapping(target = "idVehicule", source = "vehicule.idVehicule")
    @Mapping(target = "immatriculation", source = "vehicule.immatriculation")
    @Mapping(target = "idClient", source = "client.idClient")
    @Mapping(target = "nomClient", source = "client.nom")
    @Mapping(target = "prenomClient", source = "client.prenom")
    ReservationDTO toDto(Reservation reservation);
}
