package com.kimenyu.mojanexus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
    @JsonIgnore
    private Apartment apartment;

    private String leaseImageUrl;
    private String leasePdfUrl;

    // Constructors, getters, setters

    @Override
    public String toString() {
        return "Lease{" +
                "id=" + id +
                ", user=" + user +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalRent=" + totalRent +
                ", deposit=" + deposit +
                ", rentPaid=" + rentPaid +
                ", depositPaid=" + depositPaid +
                ", isPaid=" + isPaid +
                ", isDepositPaid=" + isDepositPaid +
                ", isTerminated=" + isTerminated +
                ", isDepositReturned=" + isDepositReturned +
                ", isRentReturned=" + isRentReturned +
                ", isDepositReturnedToTenant=" + isDepositReturnedToTenant +
                ", isRentReturnedToTenant=" + isRentReturnedToTenant +
                ", isDepositReturnedToOwner=" + isDepositReturnedToOwner +
                ", garbageFee=" + garbageFee +
                ", waterFee=" + waterFee +
                ", electricityFee=" + electricityFee +
                ", otherFee=" + otherFee +
                ", apartment=" + apartment +
                ", leaseImageUrl='" + leaseImageUrl + '\'' +
                ", leasePdfUrl='" + leasePdfUrl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(Double totalRent) {
        this.totalRent = totalRent;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getRentPaid() {
        return rentPaid;
    }

    public void setRentPaid(Double rentPaid) {
        this.rentPaid = rentPaid;
    }

    public Double getDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(Double depositPaid) {
        this.depositPaid = depositPaid;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Boolean getIsDepositPaid() {
        return isDepositPaid;
    }

    public void setIsDepositPaid(Boolean isDepositPaid) {
        this.isDepositPaid = isDepositPaid;
    }

    public Boolean getIsTerminated() {
        return isTerminated;
    }

    public void setIsTerminated(Boolean isTerminated) {
        this.isTerminated = isTerminated;
    }

    public Boolean getIsDepositReturned() {
        return isDepositReturned;
    }

    public void setIsDepositReturned(Boolean isDepositReturned) {
        this.isDepositReturned = isDepositReturned;
    }

    public Boolean getIsRentReturned() {
        return isRentReturned;
    }

    public void setIsRentReturned(Boolean isRentReturned) {
        this.isRentReturned = isRentReturned;
    }

    public Boolean getIsDepositReturnedToTenant() {
        return isDepositReturnedToTenant;
    }

    public void setIsDepositReturnedToTenant(Boolean isDepositReturnedToTenant) {
        this.isDepositReturnedToTenant = isDepositReturnedToTenant;
    }

    public Boolean getIsRentReturnedToTenant() {
        return isRentReturnedToTenant;
    }

    public void setIsRentReturnedToTenant(Boolean isRentReturnedToTenant) {
        this.isRentReturnedToTenant = isRentReturnedToTenant;
    }

    public Boolean getIsDepositReturnedToOwner() {
        return isDepositReturnedToOwner;
    }

    public void setIsDepositReturnedToOwner(Boolean isDepositReturnedToOwner) {
        this.isDepositReturnedToOwner = isDepositReturnedToOwner;
    }

    public Double getGarbageFee() {
        return garbageFee;
    }

    public void setGarbageFee(Double garbageFee) {
        this.garbageFee = garbageFee;
    }

    public Double getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(Double waterFee) {
        this.waterFee = waterFee;
    }

    public Double getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(Double electricityFee) {
        this.electricityFee = electricityFee;
    }

    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getLeaseImageUrl() {
        return leaseImageUrl;
    }

    public void setLeaseImageUrl(String leaseImageUrl) {
        this.leaseImageUrl = leaseImageUrl;
    }

    public String getLeasePdfUrl() {
        return leasePdfUrl;
    }

    public void setLeasePdfUrl(String leasePdfUrl) {
        this.leasePdfUrl = leasePdfUrl;
    }

    public void calculateTotalRent() {
        this.totalRent = this.totalRent + this.garbageFee + this.waterFee + this.electricityFee + this.otherFee;
    }
}
