package com.example.ticketbooking.services;

import com.example.ticketbooking.handler.OverlappingSeatsHandler;
import com.example.ticketbooking.model.Seat;
import com.example.ticketbooking.model.SeatStateEnum;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.List;

@RequiredArgsConstructor
public class SeatSelectorService {

    private final OverlappingSeatsHandler overlappingSeatsHandler;
    public List<Seat> bookSeats(final List<Seat> seats){

        val availableSeats = selectSeatBasedOnState(seats, SeatStateEnum.AVAILABLE);
        val unavailableSeats = selectSeatBasedOnState(seats, SeatStateEnum.NOT_AVAILABLE);
        lockSeats(availableSeats);
        overlappingSeatsHandler.apply(unavailableSeats);
        return availableSeats;

    }

    private List<Seat> selectSeatBasedOnState(final List<Seat> seats, final  SeatStateEnum state){
        return seats.stream().filter(seat -> seat.getState().equals(state)).toList();
    }

    private void lockSeats(final List<Seat> availableSeats){
        availableSeats.forEach(seat -> seat.setState(SeatStateEnum.NOT_AVAILABLE));
    }
}
