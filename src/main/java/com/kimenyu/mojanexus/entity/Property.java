package com.kimenyu.mojanexus.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kimenyu.mojanexus.enums.PropertyType;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    private String name;
    private String description;
    private Double size;
    private String location;
    private Double price;
    private String propertyImageUrl;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Apartment> apartments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")

    @JsonIgnore
    private Owner owner;
}