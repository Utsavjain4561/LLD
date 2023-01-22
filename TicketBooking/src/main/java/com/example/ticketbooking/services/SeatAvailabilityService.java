package com.example.ticketbooking.services;

import com.example.ticketbooking.model.Seat;
import com.example.ticketbooking.model.SeatStateEnum;
import com.example.ticketbooking.model.Show;

import java.util.List;
import java.util.stream.Collectors;

public class SeatAvailabilityService {
    public List<Seat> getAvailableSeats(final Show show) {
        return show.getSeats().stream().filter(seat -> seat.getState().equals(SeatStateEnum.AVAILABLE))
                .collect(Collectors.toList());
    }
}
