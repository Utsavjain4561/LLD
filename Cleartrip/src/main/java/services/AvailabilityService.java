package services;

import dao.Storage;
import lombok.NonNull;
import lombok.val;
import models.Booking;
import models.Hotel;
import models.Room;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AvailabilityService {
    private final Map<Room, Booking> roomBookingMap = Storage.getRoomBookings();
   public List<Room> getAvailableRooms(@NonNull final Hotel hotel, @NonNull final String roomType) {
       return hotel.getRooms().values().stream().filter(Room::getAvailable).filter(room -> room.getType().equals(roomType))
       .collect(Collectors.toList());
   }
    public List<Room> getAvailableRooms(@NonNull final Hotel hotel, @NonNull final Date checkIn, @NonNull final Date checkOut) {
        return hotel.getRooms().values().stream().filter(Room::getAvailable).filter(room -> isBooked(room, checkIn, checkOut))
                .collect(Collectors.toList());
    }

    private Boolean isBooked(final Room room, final Date checkIn, final Date checkOut){
        if(!roomBookingMap.containsKey(room)) return Boolean.TRUE;
        val booking = roomBookingMap.get(room);
        val start = booking.getCheckInDate();
        val end = booking.getCheckOutDate();
        return  start.after(checkOut) || checkIn.after(end);
    }
}
