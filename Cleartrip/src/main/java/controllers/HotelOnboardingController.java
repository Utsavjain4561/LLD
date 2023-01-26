package controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import models.Hotel;
import models.User;
import services.HotelOnboardingService;
import services.InventoryManagerService;

import java.util.Set;
@RequiredArgsConstructor
public class HotelOnboardingController {
    private final HotelOnboardingService hotelOnboardingService;
    private final InventoryManagerService inventoryManagerService;
    public Hotel onboardProperty(@NonNull final User user, @NonNull final String title, @NonNull final String city, @NonNull final Integer rating,
                                 @NonNull final Set<String> roomTypes) {

        return hotelOnboardingService.onboardProperty(user, title, city, rating, roomTypes);
    }

    public void addInventory(@NonNull final User user, @NonNull final String hotelId, @NonNull final String roomType, @NonNull final Double price,
                             @NonNull final Integer roomsAvailable) {
        inventoryManagerService.addInventory(user, hotelId, roomType, price, roomsAvailable);
    }
}
