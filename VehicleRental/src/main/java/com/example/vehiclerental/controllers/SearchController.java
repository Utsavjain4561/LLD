package com.example.vehiclerental.controllers;

import com.example.vehiclerental.model.Vehicle;
import com.example.vehiclerental.services.SearchInventoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SearchController {
    private final SearchInventoryService searchInventoryService;

    public List<Vehicle> searchVehicles() {
        return searchInventoryService.searchVehicles();
    }
    public List<Vehicle> searchVehicles(@NonNull final String userId) {
        return searchInventoryService.searchVehicles(userId);
    }
}
