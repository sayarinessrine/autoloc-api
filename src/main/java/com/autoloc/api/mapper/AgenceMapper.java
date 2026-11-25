package com.autoloc.api.mapper;

import com.autoloc.api.domain.Agence;
import com.autoloc.api.dto.AgenceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgenceMapper extends EntityMapper<Agence, AgenceDTO> {

    @Override
    AgenceDTO toDto(Agence agence);
}
