package services;

import dao.Storage;
import enums.UserType;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Hotel;
import models.User;

import java.util.*;

@Slf4j
public class HotelOnboardingService {
    private final Map<User, List<Hotel>> properties = Storage.getProperties();
    private final Map<String, Hotel> hotels = Storage.getHotels();

    public Hotel onboardProperty(final User user, final String title, final String city, final Integer rating, final Set<String> roomTypes) {
        if(user.getType().equals(UserType.ADMIN)){
            val hotel = new Hotel(title, city, rating, roomTypes, user);
            val properties = Optional.ofNullable(this.properties.get(user)).orElse(new ArrayList<>());
            properties.add(hotel);
            this.properties.put(user, properties);
            hotels.putIfAbsent(hotel.getTitle(), hotel);
            log.info("Hotel: {} onboarded successfully by user: {}", hotel.toString(), user.toString());
            return hotel;
        }else{
            throw new UnsupportedOperationException("User is not allowed to onboard hotel");
        }

    }
}
