package com.kimenyu.mojanexus.dto.propertyDto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kimenyu.mojanexus.entity.Apartment;
import com.kimenyu.mojanexus.entity.Owner;
import com.kimenyu.mojanexus.enums.PropertyType;

import lombok.Data;

@Data
public class PropertyDto {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Double size;
    private String location;
    private Double price;
    private String propertyImageUrl;
    private PropertyType propertyType;
    private List<Apartment> apartments;

    @JsonIgnore
    private Owner owner;
}