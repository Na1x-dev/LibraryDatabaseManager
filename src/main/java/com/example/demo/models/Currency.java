package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "currencies")
@JsonIgnoreProperties("hibernateLazyInitializer")
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

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "account_id")
    @NonNull
    Account account;
}
