package com.kimenyu.mojanexus.service.ApartmentsServices;

import java.util.List;

import com.kimenyu.mojanexus.dto.Apartment.ApartmentDto;
import com.kimenyu.mojanexus.entity.Apartment;

public interface ApartmentService {

    Apartment createApartment(ApartmentDto apartmentDto, String username);

    List<Apartment> listApartmentsByOwner(String authenticatedUsername);


    List<Apartment> listAllApartments();

    Apartment updateApartmentById(Long id, ApartmentDto apartmentDto);

}
