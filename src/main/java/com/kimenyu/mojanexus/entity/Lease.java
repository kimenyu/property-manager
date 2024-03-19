package com.kimenyu.mojanexus.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private User user;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    private String leaseImageUrl;
    private String leasePdfUrl;

    public void calculateTotalRent() {
        // Calculate total rent based on apartment rent, garbage fees, electricity fees, water fees, and other charges
        totalRent = apartment.getRent() + garbageFee + electricityFee + waterFee + otherFee;
    }

    public Double getBalance() {
        return totalRent - rentPaid;
    }


    public void payRent(Double amount) {
        rentPaid += amount;
        if (rentPaid >= totalRent) {
            isPaid = true;
        }
    }

    public void payDeposit(Double amount) {
        depositPaid += amount;
        if (depositPaid >= deposit) {
            isDepositPaid = true;
        }
    }

    public void terminateLease() {
        isTerminated = true;
    }

    public void returnDepositToTenant() {
        isDepositReturnedToTenant = true;
    }

    public void returnRentToTenant() {
        isRentReturnedToTenant = true;
    }

    public void returnDepositToOwner() {
        isDepositReturnedToOwner = true;
    }

    public void returnRentToOwner() {
        isRentReturned = true;
    }

    public void returnDeposit() {
        isDepositReturned = true;
    }

    public void returnRent() {
        isRentReturned = true;
    }

    public boolean isLeaseActive() {
        return !isTerminated && LocalDate.now().isBefore(endDate);
    }

    public boolean isLeaseExpired() {
        return LocalDate.now().isAfter(endDate);
    }

    public boolean isDepositReturned() {
        return isDepositReturned;
    }

    public boolean isRentReturned() {
        return isRentReturned;
    }

    public boolean isDepositReturnedToTenant() {
        return isDepositReturnedToTenant;
    }
}
