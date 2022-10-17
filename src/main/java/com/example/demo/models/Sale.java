package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sales")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Sale {
    @Id
    @Column(name = "sale_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long saleId;

    @Column(name = "sale_date")
    @NonNull
    Date saleDate;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "book_id")
    @NonNull
    Book book;

    @NonNull
    @Column(name = "price")
    Double price;

    @Column(name = "amount")
    @NonNull
    Long amount;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "client_id")
    @NonNull
    Client client;

}
