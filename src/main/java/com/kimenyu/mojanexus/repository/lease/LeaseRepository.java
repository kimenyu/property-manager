package com.kimenyu.mojanexus.repository.lease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimenyu.mojanexus.entity.Lease;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long>{

}
