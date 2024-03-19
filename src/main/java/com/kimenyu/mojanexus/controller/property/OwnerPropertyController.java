package com.kimenyu.mojanexus.controller.property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        String authenticatedOwnerUsername = authentication.getName();

        // Set the owner username in the property DTO
        propertyDto.setName(authenticatedOwnerUsername);

        // Create the property using the PropertyService
        Property createdProperty = ownerPropertyService.createProperty(propertyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }

    @GetMapping("/list/my-properties")
    public ResponseEntity<List<Property>> listMyProperties() {
        // Get the currently authenticated owner's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedOwnerUsername = authentication.getName();

        // List the properties for the authenticated owner using the PropertyService
        List<Property> properties = ownerPropertyService.listPropertiesByOwner(authenticatedOwnerUsername);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/list/all-properties")
    public ResponseEntity<List<Property>> listAllProperties() {
        // List all properties using the PropertyService
        List<Property> properties = ownerPropertyService.listAllProperties();
        return ResponseEntity.ok(properties);
    }

    @PutMapping("/update/property/{id}")
    public ResponseEntity<Property> updatePropertyById(@PathVariable Long id, @RequestBody PropertyDto propertyDto) {
        // Get the currently authenticated owner's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedOwnerUsername = authentication.getName();

        // Update the property by ID for the authenticated owner
        Property updatedProperty = ownerPropertyService.updatePropertyById(id, propertyDto, authenticatedOwnerUsername);
        
        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/delete/property/{id}")
    public ResponseEntity<Void> deletePropertyById(@PathVariable Long id) {
        // Get the currently authenticated owner's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedOwnerUsername = authentication.getName();

        // Delete the property by ID for the authenticated owner
        ownerPropertyService.deletePropertyById(id, authenticatedOwnerUsername);
        
        return ResponseEntity.noContent().build();
    }


}
