package com.example.vehiclerental.model;

import com.example.vehiclerental.enums.VehicleStateEnum;
import com.example.vehiclerental.enums.VehicleTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
public class Vehicle {
    private final String id;
    private final String name;
    private final VehicleTypeEnum type;
    @Builder.Default
    @Setter
    private VehicleStateEnum state = VehicleStateEnum.AVAILABLE;
}
