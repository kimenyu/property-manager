package com.kimenyu.mojanexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kimenyu.mojanexus.dto.ReqRes;
import com.kimenyu.mojanexus.entity.User;
import com.kimenyu.mojanexus.repository.UserRepository;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private UserRepository ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            User ourUsers = new User();
            ourUsers.setUsername(registrationRequest.getUsername());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            User ourUserResult = ourUserRepo.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getTenantId()>0) {
                resp.setOurUsers(ourUserResult);
                resp.setMessage("Tenant Saved Successfully");
                resp.setStatusCode(201);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes signIn(ReqRes signinRequest){
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
            var user = ourUserRepo.findByUsername(signinRequest.getUsername());
            System.out.println("Tenant IS: "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Tenant Successfully Signed In");
            System.out.println("Tenant Successfully Signed In");
            System.out.println(response);
            System.out.println("................");
            System.out.println(jwt);
            System.out.println("................");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        String username = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        User users = ourUserRepo.findByUsername(username);
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Tenant successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}