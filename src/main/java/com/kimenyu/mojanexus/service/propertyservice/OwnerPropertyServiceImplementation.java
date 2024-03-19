package com.kimenyu.mojanexus.service.propertyservice;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimenyu.mojanexus.dto.propertyDto.PropertyDto;
import com.kimenyu.mojanexus.entity.Owner;
import com.kimenyu.mojanexus.entity.Property;
import com.kimenyu.mojanexus.repository.OwnerRepository;
import com.kimenyu.mojanexus.repository.property.PropertyRepository;

@Service
public class OwnerPropertyServiceImplementation implements OwnerPropertyService{

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Property createProperty(PropertyDto propertyDto) {
        Owner owner = ownerRepository.findByUsername(propertyDto.getName());
        if (owner == null) {
            throw new RuntimeException("Owner not found with username: " + propertyDto.getName());
        }

        // Create a new Property entity and set its owner
        Property property = new Property();
        BeanUtils.copyProperties(propertyDto, property);
        property.setOwner(owner);

        // Save the property to the database
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> listPropertiesByOwner(String username) {
        Owner owner = ownerRepository.findByUsername(username);
        if (owner == null) {
            throw new RuntimeException("Owner not found with username: " + username);
        }

        return propertyRepository.findByOwner(owner);
    }


    @Override
    public List<Property> listAllProperties() {
        return propertyRepository.findAll();
    }

}
