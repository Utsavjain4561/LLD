package com.example.ticketbooking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class Show {
    private final Movie movie;
    private final Screen screen;
    private final Instant startTme;
    private final Instant endTime;
    private final List<Seat> seats;
}
