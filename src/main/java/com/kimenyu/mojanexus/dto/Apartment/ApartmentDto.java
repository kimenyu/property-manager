package com.kimenyu.mojanexus.dto.Apartment;


import com.kimenyu.mojanexus.enums.ApartmentType;

import lombok.Data;

@Data
public class ApartmentDto {

    private Long apartmentId;
    private ApartmentType apartmentType;
    private Double size;
    private Double price;
    private String apartmentImageUrl;
    private Boolean isAvailable;
    private Long propertyId;
}
