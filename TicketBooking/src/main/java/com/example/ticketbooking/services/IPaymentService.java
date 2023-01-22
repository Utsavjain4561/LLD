package com.example.ticketbooking.services;

import com.example.ticketbooking.model.BookingSession;
import lombok.NonNull;

public interface IPaymentService {
    BookingSession pay(final BookingSession session);
}
