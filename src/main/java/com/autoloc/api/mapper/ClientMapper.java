package com.autoloc.api.mapper;

import com.autoloc.api.domain.Client;
import com.autoloc.api.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<Client, ClientDTO> {

    @Override
    @Mapping(target = "dateInscription", source = "dateInscription", dateFormat = "dd/MM/yyyy")
    ClientDTO toDto(Client client);
}
