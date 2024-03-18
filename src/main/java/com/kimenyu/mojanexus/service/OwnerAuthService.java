package com.kimenyu.mojanexus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kimenyu.mojanexus.dto.ReqResOwner;
import com.kimenyu.mojanexus.entity.Owner;
import com.kimenyu.mojanexus.repository.OwnerRepository;

import java.util.HashMap;

@Service
public class OwnerAuthService {

    @Autowired
    private OwnerRepository ownerRepo;
    
    @Autowired
    private JWTUtilsOwner jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqResOwner register(ReqResOwner registrationRequest){
        ReqResOwner resp = new ReqResOwner();
        try {
            Owner ourUsers = new Owner();
            ourUsers.setUsername(registrationRequest.getUsername());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            Owner ourUserResult = ownerRepo.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getId()>0) {
                resp.setOurOwners(ourUserResult);
                resp.setMessage("Owner Saved Successfully");
                resp.setStatusCode(201);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqResOwner login(ReqResOwner signinRequest){
        ReqResOwner response = new ReqResOwner();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
            var user = ownerRepo.findByUsername(signinRequest.getUsername());
            System.out.println("USER IS: "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Owner successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqResOwner ownerrefreshToken(ReqResOwner refreshTokenReqiest){
        ReqResOwner response = new ReqResOwner();
        String username = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        Owner users = ownerRepo.findByUsername(username);
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Owner Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}