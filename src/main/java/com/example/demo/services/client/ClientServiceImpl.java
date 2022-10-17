package com.example.demo.services.client;

import com.example.demo.models.Book;
import com.example.demo.models.Client;
import com.example.demo.repositories.book.BookJpaRepository;
import com.example.demo.repositories.client.ClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientJpaRepository clientRepository;

    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }
}
