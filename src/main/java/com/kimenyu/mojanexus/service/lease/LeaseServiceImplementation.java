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
    public Lease createLeaseForUser(User currentUser, LeaseDto leaseDto) {
        Apartment apartment = apartmentRepository.findById(leaseDto.getApartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Apartment not found"));

        if (!apartment.getIsAvailable()) {
            throw new IllegalStateException("Apartment is not available for lease.");
        }

        Lease lease = new Lease();
        lease.setUser(currentUser);
        lease.setApartment(apartment);
        lease.setStartDate(leaseDto.getStartDate());

        // Update apartment availability
        apartment.setIsAvailable(false);
        apartmentRepository.save(apartment);

        // Save the lease
        leaseRepository.save(lease);

        return lease;
    }

    
    
}
