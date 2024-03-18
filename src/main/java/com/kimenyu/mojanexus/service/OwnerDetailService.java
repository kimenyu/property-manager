package com.kimenyu.mojanexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kimenyu.mojanexus.repository.OwnerRepository;


@Service
public class OwnerDetailService implements UserDetailsService {

    @Autowired
    private OwnerRepository ownerRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ownerRepository.findByUsername(username);
    }
}
