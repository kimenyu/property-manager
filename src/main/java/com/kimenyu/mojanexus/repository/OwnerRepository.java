package com.kimenyu.mojanexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimenyu.mojanexus.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByUsername(String username);
}
