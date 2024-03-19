package com.kimenyu.mojanexus.service.propertyservice;

import java.util.List;

import com.kimenyu.mojanexus.dto.propertyDto.PropertyDto;
import com.kimenyu.mojanexus.entity.Property;

public interface OwnerPropertyService {

    Property createProperty(PropertyDto propertyDto);

    List<Property> listPropertiesByOwner(String username);

    List<Property> listAllProperties();

    Property updatePropertyById(Long id, PropertyDto propertyDto, String username);

}
