package com.example.vehiclerental.services;

import com.example.vehiclerental.enums.BookingMode;
import com.example.vehiclerental.enums.VehicleStateEnum;
import com.example.vehiclerental.model.Booking;
import com.example.vehiclerental.model.SelectedVehicle;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class BookingManagerService {
    private final InventoryManagerService inventoryManagerService;
    @Getter
    private final Map<String, List<SelectedVehicle>> bookedVehicles = new HashMap<>();

    public Booking book(@NonNull final SelectedVehicle selectedVehicle, @NonNull final String userId,
                        @NonNull final BookingMode bookingMode) {
        // add vehicle to booking history
        val selectedVehicles = Optional.ofNullable(bookedVehicles.get(userId)).orElse(new ArrayList<>());
        selectedVehicles.add(selectedVehicle);
        bookedVehicles.putIfAbsent(userId, selectedVehicles);
        // mark vehicle as not available and update the inventory
        val vehicle = selectedVehicle.getVehicle();
        vehicle.setState(VehicleStateEnum.UNAVAILABLE);
        inventoryManagerService.updateInventory(vehicle);
        // return a booking
        return Booking.builder()
                .bookingId(UUID.randomUUID().toString())
                .bookingDate(Instant.now())
                .selectedVehicle(selectedVehicle)
                .userId(userId)
                .bookingMode(bookingMode)
                .build();
    }

    public void cancel(@NonNull final Booking booking){
        val userId = booking.getUserId();
        val selectedVehicle = booking.getSelectedVehicle();
        val vehicles = bookedVehicles.get(userId);
        vehicles.remove(selectedVehicle);
        val vehicle = selectedVehicle.getVehicle();
        vehicle.setState(VehicleStateEnum.AVAILABLE);
        inventoryManagerService.updateInventory(vehicle);

    }
}
