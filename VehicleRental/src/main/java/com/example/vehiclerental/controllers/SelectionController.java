package com.example.vehiclerental.controllers;

import com.example.vehiclerental.model.SelectedVehicle;
import com.example.vehiclerental.model.Vehicle;
import com.example.vehiclerental.services.SelectionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SelectionController {
    private final SelectionService selectionService;
    public SelectedVehicle selectVehicle(@NonNull final Vehicle vehicle) {
        return selectionService.apply(vehicle);
    }
}
