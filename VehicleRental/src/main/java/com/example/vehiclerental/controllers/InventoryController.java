package com.example.vehiclerental.controllers;

import com.example.vehiclerental.exceptions.AuthorizationException;
import com.example.vehiclerental.model.Admin;
import com.example.vehiclerental.model.User;
import com.example.vehiclerental.model.Vehicle;
import com.example.vehiclerental.services.InventoryManagerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InventoryController {
    private final InventoryManagerService inventoryManagerService;

    public void addVehicle(@NonNull final User adminUser, @NonNull final Vehicle vehicle) throws Exception {
        if(adminUser instanceof Admin){
            inventoryManagerService.addVehicle(vehicle);
        }else{
            throw new AuthorizationException("User is not authorized to perform this action");
        }
    }
}
