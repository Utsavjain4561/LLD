package com.example.ticketbooking.controller;

import com.example.ticketbooking.model.BookingSession;
import com.example.ticketbooking.model.Seat;
import com.example.ticketbooking.model.Show;
import com.example.ticketbooking.model.User;
import com.example.ticketbooking.services.SeatAvailabilityService;
import com.example.ticketbooking.services.SeatSelectorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.List;


@RequiredArgsConstructor
public class BookingController {
    private final SeatAvailabilityService seatAvailabilityService;
    private final SeatSelectorService seatSelectorService;

    public BookingSession bookShow(@NonNull final User user, @NonNull final Show show) {
        val availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return BookingSession.builder()
                .user(user)
                .show(show)
                .availableSeats(availableSeats)
                .build();
    }

    public BookingSession bookSeats(@NonNull final BookingSession session, @NonNull final List<Seat> seats) {
        val selectedSeats = seatSelectorService.bookSeats(seats);
        return session.toBuilder().selectedSeats(selectedSeats).build();
    }


}
