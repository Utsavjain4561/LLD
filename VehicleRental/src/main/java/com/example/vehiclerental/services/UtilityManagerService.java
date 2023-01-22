package com.example.vehiclerental.services;

import com.example.vehiclerental.model.SelectedVehicle;
import com.example.vehiclerental.model.Utility;
import lombok.NonNull;

import java.util.List;

public class UtilityManagerService {
    public SelectedVehicle addUtility(@NonNull final SelectedVehicle selectedVehicle, @NonNull final List<Utility> utilities) {
        return selectedVehicle.toBuilder().utilities(utilities).build();
    }
}
