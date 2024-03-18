package com.kimenyu.mojanexus.repository.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimenyu.mojanexus.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{

}
