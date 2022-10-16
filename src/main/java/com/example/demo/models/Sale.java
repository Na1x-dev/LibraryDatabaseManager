package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sales")
public class Sale {
    @Id
    @Column(name = "sale_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long saleId;

    @Column(name = "sale_date")
    @NonNull
    Date saleDate;

    @NonNull
    Book book;

    @NonNull
    @Column(name = "price")
    Double price;

    @Column(name = "amount")
    @NonNull
    Long amount;

    @NonNull
    Client client;

}
