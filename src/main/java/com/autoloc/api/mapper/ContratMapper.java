package com.autoloc.api.mapper;

import com.autoloc.api.domain.Contrat;
import com.autoloc.api.dto.ContratDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContratMapper extends EntityMapper<Contrat, ContratDTO> {

    @Override
    @Mapping(target = "idReservation", source = "reservation.idReservation")
    ContratDTO toDto(Contrat contrat);
}
