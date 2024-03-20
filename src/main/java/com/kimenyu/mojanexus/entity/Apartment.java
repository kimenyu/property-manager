package com.kimenyu.mojanexus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kimenyu.mojanexus.enums.ApartmentType;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartmentId;

    private ApartmentType apartmentType;
    private Double size;
    private Double rent;
    private String apartmentImageUrl;
    private String apartmentVideoUrl;
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @JsonIgnore
    private Property property;

    @OneToMany(mappedBy = "apartment")
    @JsonIgnore
    private List<Lease> leases;

    // Constructors, getters, setters


    public void assignToUser(User user) {
        if (this.getIsAvailable()) {
            this.setIsAvailable(false); // Update availability to false
            user.setApartment(this); // Assign the user to this apartment
        } else {
            throw new IllegalStateException("Apartment is not available for assignment.");
        }
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getApartmentImageUrl() {
        return apartmentImageUrl;
    }

    public void setApartmentImageUrl(String apartmentImageUrl) {
        this.apartmentImageUrl = apartmentImageUrl;
    }

    public String getApartmentVideoUrl() {
        return apartmentVideoUrl;
    }

    public void setApartmentVideoUrl(String apartmentVideoUrl) {
        this.apartmentVideoUrl = apartmentVideoUrl;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<Lease> getLeases() {
        return leases;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", apartmentType=" + apartmentType +
                ", size=" + size +
                ", rent=" + rent +
                ", apartmentImageUrl='" + apartmentImageUrl + '\'' +
                ", apartmentVideoUrl='" + apartmentVideoUrl + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
