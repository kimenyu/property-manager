package com.kimenyu.mojanexus.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kimenyu.mojanexus.config.RoleDeserializer;

@JsonDeserialize(using = RoleDeserializer.class)
public enum Role {
    OWNER,
    TENANT
}
