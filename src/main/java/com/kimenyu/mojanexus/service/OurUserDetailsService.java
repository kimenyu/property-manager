package com.kimenyu.mojanexus.service;


import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kimenyu.mojanexus.entity.User;
import com.kimenyu.mojanexus.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;


import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
@Service
public class OurUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // public User getAuthenticatedUser(String token) {
    //     Claims claims = extractClaims(token);
    //     String username = claims.getSubject();
    //     User user = userRepository.findByUsername(username);
    //     if (user == null) {
    //         throw new UsernameNotFoundException("User not found with username: " + username);
    //     }
    //     return user;
    // }
    // private Claims extractClaims(String token) {
    //     return Jwts.parser().setSigningKey(getKey()).parseClaimsJwt(token).getBody();
    // }

    // private SecretKey getKey() {
    //     byte[] secret = Base64.getDecoder().decode("843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3".getBytes(StandardCharsets.UTF_8));
    //     return new SecretKeySpec(secret, "HmacSHA256");
    // }
}