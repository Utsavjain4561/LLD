package com.example.ticketbooking.services;

import com.example.ticketbooking.model.Seat;
import com.example.ticketbooking.model.SeatStateEnum;

import java.util.List;

public class SeatDeselectorService {
    public void unlockSeats(final List<Seat> availableSeats){
        availableSeats.forEach(seat -> seat.setState(SeatStateEnum.AVAILABLE));
    }
}
