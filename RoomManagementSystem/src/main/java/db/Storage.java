package db;

import models.Booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Storage {

    private static Map<String, Booking> bookings;
    private static Map<String, List<Booking>> userBookings;

    private Storage() {
    }

    public static Map<String, Booking> getBookings() {
        if (Objects.isNull(bookings)) {
            bookings = new HashMap<>();
        }
        return bookings;
    }
    public static Map<String, List<Booking>> getUserBookings() {
        if (Objects.isNull(userBookings)) {
            userBookings = new HashMap<>();
        }
        return userBookings;
    }
}
