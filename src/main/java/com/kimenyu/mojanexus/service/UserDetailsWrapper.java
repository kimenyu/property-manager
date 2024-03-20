// package com.kimenyu.mojanexus.service;

// import java.util.Arrays;
// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.kimenyu.mojanexus.entity.User;
// import com.kimenyu.mojanexus.enums.Role;

// public class UserDetailsWrapper implements UserDetails {

//     private User user;
//     private Role role;

//     public UserDetailsWrapper(User user) {
//         this.user = user;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
//     }

//     @Override
//     public String getPassword() {
//         return user.getPassword();
//     }

//     @Override
//     public String getUsername() {
//         return user.getUsername();
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
