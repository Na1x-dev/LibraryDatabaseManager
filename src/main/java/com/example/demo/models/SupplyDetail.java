package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "supply_details")
public class SupplyDetail {
    @Id
    @Column(name="supply_detail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplyDetailId;

    @NonNull
    Book book;
}
