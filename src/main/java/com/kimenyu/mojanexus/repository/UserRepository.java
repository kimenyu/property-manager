package com.kimenyu.mojanexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimenyu.mojanexus.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
