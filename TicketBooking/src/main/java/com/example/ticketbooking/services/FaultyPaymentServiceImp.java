package com.example.ticketbooking.services;

import com.example.ticketbooking.model.BookingSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FaultyPaymentServiceImp implements  IPaymentService{
    @Override
    public BookingSession pay(final BookingSession session) {
        log.info("Payment for session: {} is unsuccessful", session.getSessionId());
        return session;

    }
}
