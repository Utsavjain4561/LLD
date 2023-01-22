package com.example.ticketbooking.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
@Getter
public class BookingSession {
    private final String sessionId = UUID.randomUUID().toString();
    private final Long timeout;
    private final List<Seat> availableSeats;
    private final List<Seat> selectedSeats;
    private final List<Seat> bookedSeats;
    private final Show show;
    @Builder.Default
    private final BookingStateEnum status = BookingStateEnum.NOT_BOOKED;
    private final User user;
}
