package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "currencies")
public class Currency {
    @Id
    @Column(name="currency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long currencyId;
}
