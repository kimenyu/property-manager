package com.kimenyu.mojanexus.controller.lease;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kimenyu.mojanexus.config.AuthenticationFacade;
import com.kimenyu.mojanexus.dto.lease.LeaseDto;
import com.kimenyu.mojanexus.entity.Lease;
import com.kimenyu.mojanexus.entity.User;
import com.kimenyu.mojanexus.repository.UserRepository;
import com.kimenyu.mojanexus.repository.Apartment.ApartmentRepository;
import com.kimenyu.mojanexus.service.JWTUtils;
import com.kimenyu.mojanexus.service.OurUserDetailsService;
import com.kimenyu.mojanexus.service.lease.LeaseService;

@RestController
@RequestMapping("/api/lease")
public class LeaseController {


    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private JWTUtils jwtUtils;

    private final LeaseService leaseService;

    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLeaseForCurrentUser(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody LeaseDto leaseDto
    ) {
        String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
        String username = jwtUtils.extractUsername(jwtToken);

        // Retrieve UserDetails from the user repository
        User currentUser = userRepository.findByUsername(username);

        try {
            Lease lease = leaseService.createLeaseForUser(currentUser, leaseDto);
            return ResponseEntity.ok(lease);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
