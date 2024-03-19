package com.kimenyu.mojanexus.controller.ApartmentControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimenyu.mojanexus.dto.Apartment.ApartmentDto;
import com.kimenyu.mojanexus.entity.Apartment;
import com.kimenyu.mojanexus.service.ApartmentsServices.ApartmentService;

@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @PostMapping("/create")
    public ResponseEntity<Apartment> createApartment(@RequestBody ApartmentDto apartmentDto) {
        // Get the currently authenticated owner's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedOwnerUsername = authentication.getName();

        // Create the apartment if the authenticated owner matches
        Apartment createdApartment = apartmentService.createApartment(apartmentDto, authenticatedOwnerUsername);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdApartment);
    }
}
