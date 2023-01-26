package services;

import dao.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Booking;
import models.Hotel;
import models.Room;
import models.User;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class BookingManagerService {
    private final AvailabilityService availabilityService;
    private final Map<String, Hotel> hotels = Storage.getHotels();
    private final Map<String, Booking> bookings = Storage.getBookings();
    private final Map<User, List<Booking>> userBookings = Storage.getUserBookings();
    private final Map<Room, Booking> roomBookingMap = Storage.getRoomBookings();
    public Booking book(final User user, final String hotelName, final String roomType, final Date checkIn, final Date chekOut) {
        val hotel = hotels.get(hotelName);
        val admin = hotel.getUser();
        val room = availabilityService.getAvailableRooms(hotel, roomType).get(0);
        val booking = new Booking(checkIn, chekOut, user,Map.of(room.getId(), room));
        bookings.put(booking.getId(), booking);
        val bookedByUser = Optional.ofNullable(userBookings.get(user)).orElse(new ArrayList<>());
        bookedByUser.add(booking);
        userBookings.put(user, bookedByUser);

        val adminsBooking = Optional.ofNullable(userBookings.get(admin)).orElse(new ArrayList<>());
        adminsBooking.add(booking);
        userBookings.put(admin, adminsBooking);

        roomBookingMap.put(room, booking);
        room.setAvailable(Boolean.FALSE);
        log.info("Room : {} booked for user: {}", room.toString(), user.toString());
        return booking;
    }

    public void cancelBooking(final Booking booking) {
        booking.getRoomsBooked().values().forEach(room -> room.setAvailable(Boolean.TRUE));
        bookings.remove(booking.getId());
        log.info("Booking with id: {} is cancelled ", booking.getId());

    }

    public List<Booking> showBookings(final User user) {
        val bookedByUser = userBookings.get(user);
        bookedByUser.forEach(booking -> log.info("User :{}  booked :{}", user.toString(), booking.getId()));
        return bookedByUser;
    }
}
