package com.kimenyu.mojanexus.enums;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kimenyu.mojanexus.config.PropertyTypeDeserializer;

@JsonDeserialize(using = PropertyTypeDeserializer.class)
public enum PropertyType {
    COMMERCIAL,
    RESIDENTIAL
}
