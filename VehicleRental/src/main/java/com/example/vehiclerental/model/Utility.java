package com.example.vehiclerental.model;

import com.example.vehiclerental.enums.UtilityTypeEnum;
import lombok.Builder;

@Builder(toBuilder = true)
public class Utility {
    private final String name;
    private final UtilityTypeEnum type;
}
