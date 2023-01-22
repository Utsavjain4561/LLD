package com.example.ticketbooking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
@Getter
public class Ticket {
    private final User user;
    private final List<Seat> bookedSeats;
    private final Show show;
}
