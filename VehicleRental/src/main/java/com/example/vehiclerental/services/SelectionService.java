package com.example.vehiclerental.services;

import com.example.vehiclerental.model.SelectedVehicle;
import com.example.vehiclerental.model.Vehicle;

import java.util.function.Function;

public class SelectionService implements Function<Vehicle, SelectedVehicle> {

    @Override
    public SelectedVehicle apply(Vehicle vehicle) {
        return SelectedVehicle.builder().vehicle(vehicle).build();
    }
}
