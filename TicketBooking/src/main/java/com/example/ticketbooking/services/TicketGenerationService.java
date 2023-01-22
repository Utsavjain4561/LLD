package com.example.ticketbooking.services;

import com.example.ticketbooking.model.BookingSession;
import com.example.ticketbooking.model.Ticket;
import lombok.NonNull;

public class TicketGenerationService {

    public Ticket generateTicket(@NonNull BookingSession session) {
        return new Ticket(session.getUser(), session.getBookedSeats(), session.getShow());
    }
}
