package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clientId;

    @NonNull
    @Column(name = "clientName")
    String clientName;

    @Column(name = "email")
    @NonNull
    String email;

    @Column(name = "phone")
    @NonNull
    String phone;

    @Column(name="client_address")
    @NonNull
    String clientAddress;

    @NonNull
    City city;

}
