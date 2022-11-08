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
//        client.setPhone(client.getPhone().toLowerCase().trim());
        client.setEmail(client.getEmail().toLowerCase().trim());
        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client readByClientNameAndAddress(String clientName, String clientAddress, String cityTitle, String clientEmail) {
        return clientRepository.findClientByClientNameAndClientAddressAndCity_TitleAndEmail(clientName.toLowerCase().trim(), clientAddress.toLowerCase().trim(), cityTitle.toLowerCase().trim(), clientEmail.toLowerCase().trim());
    }

    @Override
    public boolean update(Long id, Client client) {
        if (clientRepository.existsById(id)) {
            client.setClientId(id);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public Client readById(Long clientId) {
        return clientRepository.readByClientId(clientId);
    }

    @Override
    public boolean delete(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
            return true;
        }
        return false;
    }
}
