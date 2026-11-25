package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Client;
import com.autoloc.api.dto.ClientDTO;
import com.autoloc.api.mapper.ClientMapper;
import com.autoloc.api.repository.ClientRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends AbstractCrudService<Client, ClientDTO, Long> implements ClientService {

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        super(clientRepository, clientMapper, "Client");
    }

    @Override
    protected void assignId(Client client, Long id) {
        client.setIdClient(id);
    }
}
