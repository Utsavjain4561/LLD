package com.example.ticketbooking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
public class Seat {
    private final Integer seatNo;
    @Builder.Default
    @Setter
    private SeatStateEnum state = SeatStateEnum.AVAILABLE;
}
