package com.kimenyu.mojanexus.service.lease;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.kimenyu.mojanexus.dto.lease.LeaseDto;
import com.kimenyu.mojanexus.entity.Apartment;
import com.kimenyu.mojanexus.entity.Lease;
import com.kimenyu.mojanexus.entity.User;
import com.kimenyu.mojanexus.exceptions.ApartmentNotAvailableException;
import com.kimenyu.mojanexus.repository.UserRepository;
import com.kimenyu.mojanexus.repository.Apartment.ApartmentRepository;
import com.kimenyu.mojanexus.repository.lease.LeaseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LeaseServiceImplementation implements LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getAuthenticatedUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String assignApartmentAndCreateLease(User authenticatedUser, LeaseDto leaseDto) {
        Long apartmentId = leaseDto.getApartmentId();
        Optional<Apartment> optionalApartment = apartmentRepository.findById(apartmentId);

        if (optionalApartment.isEmpty()) {
            throw new ApartmentNotAvailableException("Apartment not found with id: " + apartmentId);
        }

        Apartment apartment = optionalApartment.get();

        if (!apartment.getIsAvailable()) {
            throw new ApartmentNotAvailableException("Apartment is not available for assignment.");
        }

        apartment.assignToUser(authenticatedUser); // Assign apartment to the user if available

        // Create a new lease
        Lease lease = new Lease();
        lease.setUser(authenticatedUser);
        lease.setApartment(apartment);
        lease.setStartDate(leaseDto.getStartDate());
        lease.setEndDate(leaseDto.getEndDate());
        lease.calculateTotalRent();

        leaseRepository.save(lease);

        return "Apartment assigned and lease created successfully.";
    }



    
}
