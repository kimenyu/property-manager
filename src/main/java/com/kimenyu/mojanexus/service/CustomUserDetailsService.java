package com.kimenyu.mojanexus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kimenyu.mojanexus.entity.User;
import com.kimenyu.mojanexus.entity.Owner;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OurUserDetailsService ourUserDetailsService;

    @Autowired
    private OwnerDetailService ownerDetailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if the user exists in the regular user service
        User user = (User) ourUserDetailsService.loadUserByUsername(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().toString()) // Assuming roles are stored as strings
                    .build();
        }

        // Check if the user exists in the owner service
        Owner owner = (Owner) ownerDetailService.loadUserByUsername(username);
        if (owner != null) {
            return org.springframework.security.core.userdetails.User.withUsername(owner.getUsername())
                    .password(owner.getPassword())
                    .roles(owner.getRole().toString()) // Assuming roles are stored as strings
                    .build();
        }

        // If user is not found in either service, throw UsernameNotFoundException
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
