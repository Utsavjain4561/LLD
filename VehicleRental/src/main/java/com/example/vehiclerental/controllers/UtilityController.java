package com.example.vehiclerental.controllers;

import com.example.vehiclerental.model.SelectedVehicle;
import com.example.vehiclerental.model.Utility;
import com.example.vehiclerental.services.UtilityManagerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UtilityController {
    private final UtilityManagerService utilityManagerService;
    public SelectedVehicle addUtility(@NonNull final SelectedVehicle vehicle, @NonNull final List<Utility> utilities) {
        return utilityManagerService.addUtility(vehicle, utilities);
    }
}
