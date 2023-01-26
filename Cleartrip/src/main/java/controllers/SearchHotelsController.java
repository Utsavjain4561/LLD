package controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import models.Hotel;
import services.SearchManagerService;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class SearchHotelsController {
    private final SearchManagerService searchManagerService;
    public List<Hotel> searchHotels(@NonNull Date checkInDate, @NonNull Date checkOutDate) {
        return searchManagerService.searchHotels(checkInDate, checkOutDate);
    }
    public List<Hotel> searchHotels(@NonNull Date checkInDate, @NonNull Date checkOutDate, @NonNull String city) {
        return searchManagerService.searchHotels(checkInDate, checkOutDate, city);
    }
    public List<Hotel> searchHotels(@NonNull Date checkInDate, @NonNull Date checkOutDate, @NonNull Double lowPrice, @NonNull Double highPrice) {
        return searchManagerService.searchHotels(checkInDate, checkOutDate, lowPrice, highPrice);
    }
    public List<Hotel> searchHotels(@NonNull Date checkInDate, @NonNull Date checkOutDate, @NonNull String city, @NonNull Double lowPrice, @NonNull Double highPrice) {
        return searchManagerService.searchHotels(checkInDate, checkOutDate, city, lowPrice, highPrice);
    }
}
