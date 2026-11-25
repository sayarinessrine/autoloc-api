package com.autoloc.api.mapper;

import com.autoloc.api.domain.Equipement;
import com.autoloc.api.dto.EquipementDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipementMapper extends EntityMapper<Equipement, EquipementDTO> {

    @Override
    EquipementDTO toDto(Equipement equipement);
}
