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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double rent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}