package com.kimenyu.mojanexus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimenyu.mojanexus.dto.ReqResOwner;
import com.kimenyu.mojanexus.service.OwnerAuthService;

@RestController
@RequestMapping("/auth")
public class OwnerController {

   

    @Autowired
    private OwnerAuthService ownerAuthService;


    @PostMapping("/owner/signup")
    public ResponseEntity<ReqResOwner> register(@RequestBody ReqResOwner signUpRequest){
        return ResponseEntity.ok(ownerAuthService.register(signUpRequest));
    }
    @PostMapping("/owner/signin")
    public ResponseEntity<ReqResOwner> login(@RequestBody ReqResOwner signInRequest){
        return ResponseEntity.ok(ownerAuthService.login(signInRequest));
    }
    @PostMapping("/owner/refresh")
    public ResponseEntity<ReqResOwner> ownerrefreshToken(@RequestBody ReqResOwner refreshTokenRequest){
        return ResponseEntity.ok(ownerAuthService.ownerrefreshToken(refreshTokenRequest));
    }
}