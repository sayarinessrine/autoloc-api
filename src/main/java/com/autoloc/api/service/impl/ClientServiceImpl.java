package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Client;
import com.autoloc.api.repository.ClientRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends AbstractCrudService<Client, Long> implements ClientService {

    public ClientServiceImpl(ClientRepository clientRepository) {
        super(clientRepository, "Client");
    }

    @Override
    protected void assignId(Client client, Long id) {
        client.setIdClient(id);
    }
}
