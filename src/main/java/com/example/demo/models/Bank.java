package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "banks")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Bank {
    @Id
    @Column(name = "bank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bankId;

    @Column(name = "bank_title")
    @NonNull
    String bankTitle;

    @Column(name = "bank_address")
    @NonNull
    String bankAddress;

    @Column(name="phone_number")
    @NonNull
    String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "account_id")
    @NonNull
    Account account;


}
