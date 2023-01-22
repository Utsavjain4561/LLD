package com.example.vehiclerental.services;

import com.example.vehiclerental.model.Vehicle;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;


public class InventoryManagerService {
    @Getter
    private final Map<String, Vehicle> vehicles = new HashMap<>();

    public void addVehicle(final Vehicle vehicle) {
        vehicles.putIfAbsent(vehicle.getId(), vehicle);
    }
    public void updateInventory(@NonNull final Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
    }
}
