package com.kimenyu.mojanexus.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kimenyu.mojanexus.enums.ApartmentType;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartmentId;

    private ApartmentType apartmentType;
    private Double size;
    private Double price;
    private String apartmentImageUrl;
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @JsonIgnore
    private Property property;

    @OneToMany(mappedBy = "apartment")
    private List<Lease> leases;
}
