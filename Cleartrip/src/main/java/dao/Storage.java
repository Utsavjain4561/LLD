package dao;

import models.Booking;
import models.Hotel;
import models.Room;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Storage {

    private static Map<User, List<Hotel>> properties;
    private static Map<String, Hotel> hotels;
    private static Map<String, Booking> bookings;
    private static Map<Room, Booking> roomBookingMap;
    private static Map<User, List<Booking>> userBookings;
    private Storage(){}
    public static Map<User, List<Hotel>> getProperties() {
        if(Objects.isNull(properties)){
            properties = new HashMap<>();
        }
        return properties;
    }
    public static Map<String, Hotel>  getHotels() {
        if(Objects.isNull(hotels)){
            hotels = new HashMap<>();
        }
        return hotels;
    }
    public static Map<String, Booking>  getBookings() {
        if(Objects.isNull(bookings)){
            bookings = new HashMap<>();
        }
        return bookings;
    }
    public static Map<User, List<Booking>>  getUserBookings() {
        if(Objects.isNull(userBookings)){
            userBookings = new HashMap<>();
        }
        return userBookings;
    }
    public static Map<Room, Booking>  getRoomBookings() {
        if(Objects.isNull(roomBookingMap)){
            roomBookingMap = new HashMap<>();
        }
        return roomBookingMap;
    }
}
