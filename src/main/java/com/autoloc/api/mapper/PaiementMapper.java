package com.autoloc.api.mapper;

import com.autoloc.api.domain.Paiement;
import com.autoloc.api.dto.PaiementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaiementMapper extends EntityMapper<Paiement, PaiementDTO> {

    @Override
    @Mapping(target = "idContrat", source = "contrat.idContrat")
    PaiementDTO toDto(Paiement paiement);
}
