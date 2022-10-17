package com.example.demo.services.client;

import com.example.demo.models.Book;
import com.example.demo.models.Client;

import java.util.List;

public interface ClientService {
    void create(Client client);

    List<Client> readAll();

}
