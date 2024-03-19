package com.kimenyu.mojanexus.repository.Apartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimenyu.mojanexus.entity.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long>{

}
