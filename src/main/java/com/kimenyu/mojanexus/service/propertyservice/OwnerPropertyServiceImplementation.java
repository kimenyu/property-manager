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

    @Override
    public Property updatePropertyById(Long id, PropertyDto propertyDto, String username) {
        // Find the property by ID
        Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));

        // Check if the authenticated owner matches the property's owner
        Owner owner = ownerRepository.findByUsername(username);
        if (!existingProperty.getOwner().equals(owner)) {
            throw new RuntimeException("You are not authorized to update this property");
        }

        // Update the property with new data from PropertyDto
        existingProperty.setName(propertyDto.getName());
        existingProperty.setDescription(propertyDto.getDescription());
        existingProperty.setPrice(propertyDto.getPrice());
        existingProperty.setLocation(propertyDto.getLocation());
        existingProperty.setSize(propertyDto.getSize());
        existingProperty.setPropertyType(propertyDto.getPropertyType());
        existingProperty.setPropertyImageUrl(propertyDto.getPropertyImageUrl());
        existingProperty.setType(propertyDto.getType());
        // Save the updated property
        return propertyRepository.save(existingProperty);
    }

    @Override
    public void deletePropertyById(Long id, String username) {
        // Find the property by ID
        Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));

        // Check if the authenticated owner matches the property's owner
        Owner owner = ownerRepository.findByUsername(username);
        if (!existingProperty.getOwner().equals(owner)) {
            throw new RuntimeException("You are not authorized to delete this property");
        }

        // Delete the property
        propertyRepository.delete(existingProperty);
    }

}
