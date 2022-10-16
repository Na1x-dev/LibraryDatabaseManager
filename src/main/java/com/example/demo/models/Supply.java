package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "supplies")
public class Supply {
    @Id
    @Column(name = "supply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplyId;

    @Column(name = "supply_date")
    @NonNull
    Date supplyDate;

    @NonNull
    Supplier supplier;
}
