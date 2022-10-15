package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "publishers")
public class Publisher {
    @Id
    @Column(name="publisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long publisherId;
}
