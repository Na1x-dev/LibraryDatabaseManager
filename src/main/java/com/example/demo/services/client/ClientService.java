package com.example.demo.services.client;

import com.example.demo.models.Client;
import com.example.demo.models.SupplyDetail;

import java.util.List;

public interface ClientService {
    Client create(Client client);

    List<Client> readAll();

    Client readByClientNameAndAddress(String clientName, String clientAddress, String cityTitle, String clientEmail);
    public boolean update(Long id, Client client);
}
