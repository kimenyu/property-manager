package com.kimenyu.mojanexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimenyu.mojanexus.entity.Owner;
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByUsername(String username);
}
