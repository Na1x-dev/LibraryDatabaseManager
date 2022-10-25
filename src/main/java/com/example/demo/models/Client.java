package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "clients")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clientId;

    @NonNull
    @Column(name = "clientName")
    String clientName;

    @Column(name = "email")
    @NonNull
    String email;

//    @Column(name = "phone")
//    @NonNull
//    String phone;

    @Column(name = "client_address")
    @NonNull
    String clientAddress;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false, referencedColumnName = "city_id")
    @NonNull
    City city;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Sale> sales;

    public Client() {
        city = new City();
        clientAddress = "";
        email = "";
//        phone = "";
    }
}
