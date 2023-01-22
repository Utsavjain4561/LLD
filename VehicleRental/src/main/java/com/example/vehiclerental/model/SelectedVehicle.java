package com.example.vehiclerental.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@ToString
public class SelectedVehicle {
    private final Vehicle vehicle;
    private final List<Utility> utilities;
}
