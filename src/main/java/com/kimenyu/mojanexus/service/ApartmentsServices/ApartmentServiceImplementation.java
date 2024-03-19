package com.kimenyu.mojanexus.service.ApartmentsServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimenyu.mojanexus.dto.Apartment.ApartmentDto;
import com.kimenyu.mojanexus.entity.Apartment;
import com.kimenyu.mojanexus.entity.Owner;
import com.kimenyu.mojanexus.entity.Property;
import com.kimenyu.mojanexus.repository.OwnerRepository;
import com.kimenyu.mojanexus.repository.Apartment.ApartmentRepository;

@Service
public class ApartmentServiceImplementation implements ApartmentService{

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Apartment createApartment(ApartmentDto apartmentDto, String username) {
        // Check if the authenticated owner exists
        Owner owner = ownerRepository.findByUsername(username);
        if (owner == null) {
            throw new RuntimeException("Authenticated owner not found with username: " + username);
        }
    
        // Ensure the owner has at least one property
        List<Property> properties = owner.getProperties();
        if (properties.isEmpty()) {
            throw new RuntimeException("Owner does not have any properties to assign the apartment to.");
        }
    
        // Create a new apartment entity and copy properties from the DTO
        Apartment apartment = new Apartment();
        BeanUtils.copyProperties(apartmentDto, apartment);
    
        // Set the owner's first property for the apartment (assuming at least one property exists)
        apartment.setProperty(properties.get(0));
    
        // Save the apartment
        return apartmentRepository.save(apartment);
    }

    @Override
    public List<Apartment> listApartmentsByOwner(String username) {
Owner owner = ownerRepository.findByUsername(username);
        if (owner == null) {
            throw new RuntimeException("Owner not found with username: " + username);
        }
    
        return apartmentRepository.findByPropertyOwner(owner);
    }

    @Override
    public List<Apartment> listAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment updateApartmentById(Long id, ApartmentDto apartmentDto) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if (optionalApartment.isPresent()) {
            Apartment existingApartment = optionalApartment.get();
            existingApartment.setApartmentType(apartmentDto.getApartmentType());
            existingApartment.setSize(apartmentDto.getSize());
            existingApartment.setPrice(apartmentDto.getPrice());
            existingApartment.setApartmentImageUrl(apartmentDto.getApartmentImageUrl());
            existingApartment.setIsAvailable(apartmentDto.getIsAvailable());

            return apartmentRepository.save(existingApartment);
        } else {
            throw new RuntimeException("Apartment not found with ID: " + id);
        }
    }

    @Override
    public boolean deleteApartmentById(Long id, String username) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if (optionalApartment.isPresent()) {
            Apartment existingApartment = optionalApartment.get();
            if (existingApartment.getProperty().getOwner().getUsername().equals(ownerRepository.findByUsername(username).getUsername())) {
                apartmentRepository.delete(existingApartment);
                return true;
            } else {
                throw new RuntimeException("Authenticated owner does not have permission to delete this apartment.");
            }
        } else {
            throw new RuntimeException("Apartment not found with ID: " + id);
        }
    }
    

}
