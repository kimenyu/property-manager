package com.kimenyu.mojanexus.entity;


import java.util.List;

import com.kimenyu.mojanexus.enums.PropertyType;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String type;
    private Double size;
    private String location;
    private Double price;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @OneToMany(mappedBy = "property")
    private List<Apartment> apartments;
}