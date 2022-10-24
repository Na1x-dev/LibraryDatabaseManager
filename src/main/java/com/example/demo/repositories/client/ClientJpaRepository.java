package com.example.demo.repositories.client;

import com.example.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long> {
    Client findClientByClientNameAndClientAddress(String clientName, String ClientAddress);
}