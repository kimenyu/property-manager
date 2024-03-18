package com.kimenyu.mojanexus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimenyu.mojanexus.dto.ReqRes;
import com.kimenyu.mojanexus.service.AuthService;
import com.kimenyu.mojanexus.service.OwnerAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private OwnerAuthService ownerAuthService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }


    @PostMapping("/owner/signup")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes signUpRequest){
        return ResponseEntity.ok(ownerAuthService.register(signUpRequest));
    }
    @PostMapping("/owner/signin")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(ownerAuthService.login(signInRequest));
    }
    @PostMapping("/owner/refresh")
    public ResponseEntity<ReqRes> ownerrefreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(ownerAuthService.ownerrefreshToken(refreshTokenRequest));
    }
}