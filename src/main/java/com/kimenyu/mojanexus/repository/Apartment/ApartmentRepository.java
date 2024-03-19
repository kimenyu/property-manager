package com.kimenyu.mojanexus.repository.Apartment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimenyu.mojanexus.entity.Apartment;
import com.kimenyu.mojanexus.entity.Owner;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long>{

    List<Apartment> findByPropertyOwner(Owner owner);

}
