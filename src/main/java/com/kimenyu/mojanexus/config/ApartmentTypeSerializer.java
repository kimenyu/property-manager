package com.kimenyu.mojanexus.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kimenyu.mojanexus.enums.ApartmentType;

import java.io.IOException;

public class ApartmentTypeSerializer extends JsonDeserializer<ApartmentType> {
    @Override
    public ApartmentType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return ApartmentType.valueOf(value.toUpperCase()); // Assuming your enum constants are uppercase
    }
}
