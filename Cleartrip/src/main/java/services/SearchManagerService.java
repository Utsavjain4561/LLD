package services;

import dao.Storage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Hotel;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class SearchManagerService implements ISearch{
    private final Map<String, Hotel> hotelsMap = Storage.getHotels();
    private final AvailabilityService availabilityService;
    @Override
    public List<Hotel> searchHotels(@NonNull Date checkInDate, @NonNull Date checkOutDate) {
        val hotels = hotelsMap.values().stream()
                .map(hotel -> {
                    val rooms = availabilityService.getAvailableRooms(hotel, checkInDate, checkOutDate);
                    return rooms.size() >0 ? hotel : null;
                })
                .filter(Objects::nonNull).collect(Collectors.toList());
        print(hotels);
        return hotels;
    }

    @Override
    public List<Hotel> searchHotels(@NonNull Date checkInDate, @NonNull Date checkOutDate, @NonNull String city) {
        val hotels = searchHotels(checkInDate, checkOutDate).stream().filter(hotel -> hotel.getCity().equals(city)).collect(Collectors.toList());
        print(hotels);
        return hotels;
    }

    @Override
    public List<Hotel> searchHotels(@NonNull Date checkInDate, @NonNull Date checkOutDate, @NonNull Double lowPrice, @NonNull Double highPrice) {
        val hotels=  searchHotels(checkInDate, checkOutDate).stream().filter(hotel -> {
            val rooms = hotel.getRooms().values().stream()
                    .filter(room -> room.getPrice() >=lowPrice && room.getPrice() <=highPrice)
                    .collect(Collectors.toList());
            return rooms.size()>0;
        }).collect(Collectors.toList());
        print(hotels);
        return hotels;
    }

    @Override
    public List<Hotel> searchHotels(@NonNull Date checkInDate, @NonNull Date checkOutDate, @NonNull String city, @NonNull Double lowPrice, @NonNull Double highPrice) {
        val hotels = searchHotels(checkInDate, checkOutDate, lowPrice, highPrice).stream().filter(hotel -> hotel.getCity().equals(city)).collect(Collectors.toList());
        print(hotels);
        return hotels;
    }
    private void print(final List<Hotel> hotels) {
        hotels.forEach(hotel -> log.info("Hotel: {}", hotel));
    }
}
