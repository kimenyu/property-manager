package com.kimenyu.mojanexus.entity;

import com.kimenyu.mojanexus.enums.ApartmentType;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ApartmentType apartmentType;
    private Double size;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}