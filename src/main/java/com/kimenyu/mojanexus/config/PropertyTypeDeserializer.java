package com.kimenyu.mojanexus.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kimenyu.mojanexus.enums.PropertyType;

import java.io.IOException;

public class PropertyTypeDeserializer extends JsonDeserializer<PropertyType> {
    @Override
    public PropertyType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return PropertyType.valueOf(value.toUpperCase());
    }
}
