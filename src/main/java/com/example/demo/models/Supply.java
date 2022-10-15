package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "supplies")
public class Supply {
    @Id
    @Column(name="supply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplyId;
}
