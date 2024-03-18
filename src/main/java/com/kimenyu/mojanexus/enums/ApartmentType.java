package com.kimenyu.mojanexus.enums;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kimenyu.mojanexus.config.ApartmentTypeSerializer;

@JsonDeserialize(using = ApartmentTypeSerializer.class)
public enum ApartmentType {
    STUDIO,
    ONE_BEDROOM,
    TWO_BEDROOM,
    THREE_BEDROOM,
    FOUR_BEDROOM,
    FIVE_BEDROOM
}
