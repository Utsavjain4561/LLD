package com.example.ticketbooking.services;

import com.example.ticketbooking.model.BookingSession;
import com.example.ticketbooking.model.BookingStateEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkingPaymentServiceImp implements IPaymentService{
    @Override
    public BookingSession pay(final BookingSession session) {
        log.info("Payment for session: {} is successful", session.getSessionId());
        return session.toBuilder()
                .bookedSeats(session.getSelectedSeats())
                .status(BookingStateEnum.BOOKED)
                .build();

    }
}
