package com.kimenyu.mojanexus.service.lease;

import java.time.LocalDate;

import com.kimenyu.mojanexus.dto.lease.LeaseDto;
import com.kimenyu.mojanexus.entity.Apartment;
import com.kimenyu.mojanexus.entity.Lease;
import com.kimenyu.mojanexus.entity.User;

public interface LeaseService {

    Lease createLeaseForUser(User currentUser, LeaseDto leaseDto);

}
