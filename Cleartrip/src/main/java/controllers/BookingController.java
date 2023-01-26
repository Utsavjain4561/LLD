package controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import models.Booking;
import models.User;
import services.BookingManagerService;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class BookingController {
    private final BookingManagerService bookingManagerService;
    public Booking book(final User user, final String hotelName, final String roomType, final Date checkIn, final Date chekOut) {
        return bookingManagerService.book(user, hotelName, roomType, checkIn, chekOut);
    }
    public void cancelBooking(final Booking booking) {
        bookingManagerService.cancelBooking(booking);
    }
    public List<Booking> showBooking(@NonNull final User user) {
        return bookingManagerService.showBookings(user);
    }
}
