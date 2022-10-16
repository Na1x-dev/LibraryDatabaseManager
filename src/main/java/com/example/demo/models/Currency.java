package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "currencies")
public class Currency {
    @Id
    @Column(name = "currency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long currencyId;

    @Column(name = "currency_title")
    @NonNull
    String currencyTitle;

    @Column(name = "designation")
    @NonNull
    String designation;

}
