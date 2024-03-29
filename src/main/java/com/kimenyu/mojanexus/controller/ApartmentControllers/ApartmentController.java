package com.kimenyu.mojanexus.controller.ApartmentControllers;

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

     @GetMapping("/owner/list/my-apartments")
    public ResponseEntity<List<Apartment>> listApartmentsByOwner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName(); // Assuming username is used for identification

        List<Apartment> apartments = apartmentService.listApartmentsByOwner(authenticatedUsername);
        return ResponseEntity.status(HttpStatus.OK).body(apartments);
    }

    @GetMapping("/all/list")
    public ResponseEntity<List<Apartment>> listAllApartments() {
        List<Apartment> apartments = apartmentService.listAllApartments();
        return ResponseEntity.status(HttpStatus.OK).body(apartments);
    }

    @PutMapping("/update/apartment/{id}")
    public ResponseEntity<Apartment> updateApartmentById(@PathVariable Long id, @RequestBody ApartmentDto apartmentDto) {
        Apartment updatedApartment = apartmentService.updateApartmentById(id, apartmentDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedApartment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApartmentById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedOwnerUsername = authentication.getName();

        boolean deleted = apartmentService.deleteApartmentById(id, authenticatedOwnerUsername);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Apartment deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this apartment");
        }
    }
}
