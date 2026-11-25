package com.autoloc.api.mapper;

import com.autoloc.api.domain.Employe;
import com.autoloc.api.dto.EmployeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeMapper extends EntityMapper<Employe, EmployeDTO> {

    @Override
    @Mapping(target = "idAgence", source = "agence.idAgence")
    @Mapping(target = "nomAgence", source = "agence.nom")
    EmployeDTO toDto(Employe employe);
}
