package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "suppliers")
public class Supplier {
    @Id
    @Column(name="supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplierId;
}
