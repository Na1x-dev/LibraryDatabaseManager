package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "supply_details")
public class SupplyDetail {
    @Id
    @Column(name="supply_detail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplyDetailId;
}
