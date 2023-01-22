package com.example.ticketbooking.handler;

import com.example.ticketbooking.model.Seat;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

@Slf4j
public class OverlappingSeatsHandler implements Function<List<Seat>, Void> {
    @Override
    public Void apply(List<Seat> seats) {
        seats.forEach(seat -> log.warn("Seat locked at this moment: {}", seat.toString()));
        return null;
    }
}
