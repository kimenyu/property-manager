package com.kimenyu.mojanexus.service.propertyservice;

import com.kimenyu.mojanexus.dto.propertyDto.PropertyDto;
import com.kimenyu.mojanexus.entity.Property;

public interface OwnerPropertyService {

    Property createProperty(PropertyDto propertyDto);

}
