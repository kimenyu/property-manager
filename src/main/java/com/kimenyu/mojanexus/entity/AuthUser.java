// package com.kimenyu.mojanexus.entity;

// import java.util.Arrays;
// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.kimenyu.mojanexus.enums.Role;

// public class AuthUser extends User implements UserDetails {

//     private Role role;

//     public AuthUser(String username, String password, Role role) {
//         super(username, password);
//         this.role = role;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

// }