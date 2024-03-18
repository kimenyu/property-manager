package com.kimenyu.mojanexus.controller.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimenyu.mojanexus.dto.propertyDto.PropertyDto;
import com.kimenyu.mojanexus.entity.Property;
import com.kimenyu.mojanexus.service.propertyservice.OwnerPropertyService;

@RestController
@RequestMapping("/api/property")
public class OwnerPropertyController {

    @Autowired
    private OwnerPropertyService ownerPropertyService;

    @PostMapping("/create")
    public ResponseEntity<Property> createProperty(@RequestBody PropertyDto propertyDto) {
        // Get the currently authenticated owner
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedOwnerUsername = authentication.getName(); // Assuming username is used for identification

        // Set the owner username in the property DTO
        propertyDto.setName(authenticatedOwnerUsername);

        // Create the property using the PropertyService
        Property createdProperty = ownerPropertyService.createProperty(propertyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }
}
