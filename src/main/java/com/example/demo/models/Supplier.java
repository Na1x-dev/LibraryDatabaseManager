package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {
    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplierId;

    @Column(name = "supplier_name")
    @NonNull
    String supplierName;

    @NonNull
    Account account;
}
