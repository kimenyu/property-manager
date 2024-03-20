package com.kimenyu.mojanexus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kimenyu.mojanexus.enums.PropertyType;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(exclude = "apartments")
@Accessors(chain = true)
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    private String name;
    private String description;
    private Double size;
    private String location;
    private Double price;
    private String propertyImageUrl;
    private String propertyVideoUrl;
    private Number numberOfApartments;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Apartment> apartments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Owner owner;

    // Constructors, getters, setters

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", propertyImageUrl='" + propertyImageUrl + '\'' +
                ", propertyVideoUrl='" + propertyVideoUrl + '\'' +
                ", numberOfApartments=" + numberOfApartments +
                ", propertyType=" + propertyType +
                '}';
    }
}
