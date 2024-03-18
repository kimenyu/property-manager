package com.kimenyu.mojanexus.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_owner_id")
    private Owner owner;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double rent;

    // getters and setters
}