package com.kimenyu.mojanexus.dto.lease;

import java.time.LocalDate;

import lombok.Data;


@Data
public class LeaseDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalRent;
    private Double deposit;
    private Double rentPaid;
    private Double depositPaid;
    private Boolean isPaid;
    private Boolean isDepositPaid;
    private Boolean isTerminated;
    private Boolean isDepositReturned;
    private Boolean isRentReturned;
    private Boolean isDepositReturnedToTenant;
    private Boolean isRentReturnedToTenant;
    private Boolean isDepositReturnedToOwner;
    private Double garbageFee;
    private Double waterFee;
    private Double electricityFee;
    private Double otherFee;
    private String leaseImageUrl;
    private String leasePdfUrl;
    private Long tenantId;
    private Long apartmentId;
}

