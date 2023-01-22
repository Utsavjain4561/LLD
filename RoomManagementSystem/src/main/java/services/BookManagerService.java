package services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Booking;
import models.Room;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class BookManagerService {
    public final Map<String, Booking> bookings;
    public final Map<String, List<Booking>> userBookings;
    private final AvailabilityService availabilityService;

    public Booking bookRoom(@NonNull final String userId, @NonNull final Room room, @NonNull final Integer startTime,
                            @NonNull final Integer endTime) {
        // check if the room is available or not
        if (availabilityService.isAvailable(room.getId(), startTime, endTime)) {
            val booking = new Booking(userId, startTime, endTime, room);
            bookings.put(room.getId(), booking);
            val userBookings = Optional.ofNullable(this.userBookings.get(userId)).orElse(new ArrayList<>());
            userBookings.add(booking);
            this.userBookings.put(userId, userBookings);
            return booking;
        } else {
            val bookedStart = bookings.get(room.getId()).getStartTime();
            val bookedEnd = bookings.get(room.getId()).getEndTime();
            log.error("Room with id :{} is already booked for time: {}-{}", room.getId(), bookedStart, bookedEnd);
            return null;
        }
    }

    public void cancelBooking(@NonNull final String userId, @NonNull final Booking booking){
        val roomBooked = booking.getBookedRoom();
        bookings.remove(roomBooked.getId());
        val userBookings = Optional.ofNullable(this.userBookings.get(userId)).orElse(Collections.emptyList());
        userBookings.remove(booking);
        // if all bookings of user are cancelled then remove entry of user
        if(userBookings.isEmpty()) this.userBookings.remove(userId);

        log.info("User id: {}, Booking for room : {} for time: {}-{} is cancelled", userId, roomBooked,
                booking.getStartTime(), booking.getEndTime());
    }

    public List<Booking> showBookings(@NonNull final String userId) {
        val bookings = this.userBookings.entrySet().stream()
                .filter(entry -> entry.getKey().equals(userId))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("Bookings for user id: {} are :{}", userId, bookings);
        return bookings;
    }
}
