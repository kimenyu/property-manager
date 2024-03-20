package com.kimenyu.mojanexus.controller.lease;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kimenyu.mojanexus.config.AuthenticationFacade;
import com.kimenyu.mojanexus.dto.lease.LeaseDto;
import com.kimenyu.mojanexus.entity.User;
import com.kimenyu.mojanexus.repository.UserRepository;
import com.kimenyu.mojanexus.repository.Apartment.ApartmentRepository;
import com.kimenyu.mojanexus.service.lease.LeaseService;

@RestController
@RequestMapping("/api/lease")
public class LeaseController {


    private final LeaseService leaseService;

    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignApartmentAndCreateLease(@RequestBody LeaseDto leaseDto) {
        // Retrieve authenticated user's username
        String authenticatedOwnerUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Pass the username to the service method to get the authenticated user
        User authenticatedUser = leaseService.getAuthenticatedUserByUsername(authenticatedOwnerUsername);

        // Call the service method to assign apartment and create lease
        String message = leaseService.assignApartmentAndCreateLease(authenticatedUser, leaseDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
