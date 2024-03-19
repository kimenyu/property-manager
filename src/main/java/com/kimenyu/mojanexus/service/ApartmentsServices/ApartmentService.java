package com.kimenyu.mojanexus.service.ApartmentsServices;

import com.kimenyu.mojanexus.dto.Apartment.ApartmentDto;
import com.kimenyu.mojanexus.entity.Apartment;

public interface ApartmentService {

    Apartment createApartment(ApartmentDto apartmentDto, String username);

}
