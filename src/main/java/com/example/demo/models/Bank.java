package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "banks")
public class Bank {
    @Id
    @Column(name = "bank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bankId;
}
