package com.example.vehiclerental.services;

import com.example.vehiclerental.enums.VehicleStateEnum;
import com.example.vehiclerental.model.SelectedVehicle;
import com.example.vehiclerental.model.Vehicle;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchInventoryService {
    private final InventoryManagerService inventoryManagerService;
    private final BookingManagerService bookingManagerService;

    public List<Vehicle> searchVehicles() {
        return inventoryManagerService.getVehicles().values().stream()
                .filter(vehicle -> VehicleStateEnum.AVAILABLE.equals(vehicle.getState()))
                .collect(Collectors.toList());
    }

    public List<Vehicle> searchVehicles(@NonNull final String userId) {
        return bookingManagerService.getBookedVehicles().get(userId).stream().map(SelectedVehicle::getVehicle)
                .collect(Collectors.toList());
    }
}
