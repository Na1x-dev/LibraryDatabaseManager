package com.example.demo.services.client;

import com.example.demo.models.Client;
import com.example.demo.repositories.client.ClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientJpaRepository clientRepository;

    @Override
    public Client create(Client client) {
        client.setClientName(client.getClientName().toLowerCase().trim());
        client.setClientAddress(client.getClientAddress().toLowerCase().trim());
        client.setPhone(client.getPhone().toLowerCase().trim());
        client.setEmail(client.getEmail().toLowerCase().trim());
        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client readByClientNameAndAddress(String clientName, String clientAddress) {
        return clientRepository.findClientByClientNameAndClientAddress(clientName.toLowerCase().trim(), clientAddress.toLowerCase().trim());
    }
}
