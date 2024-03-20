package com.kimenyu.mojanexus.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.kimenyu.mojanexus.entity.User;



@Component
public class AuthenticationFacade {

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User userDetails = (User) authentication.getPrincipal();
            return userDetails.getTenantId();
        }
        throw new IllegalStateException("User not authenticated");
    }
}
