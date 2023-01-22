package com.example.vehiclerental.controllers;

import com.example.vehiclerental.enums.BookingMode;
import com.example.vehiclerental.model.Booking;
import com.example.vehiclerental.model.Invoice;
import com.example.vehiclerental.model.SelectedVehicle;
import com.example.vehiclerental.services.BookingManagerService;
import com.example.vehiclerental.services.InvoiceGenerationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingController {
    private final BookingManagerService bookingManagerService;
    private final InvoiceGenerationService invoiceGenerationService;

    public Booking book(@NonNull final SelectedVehicle vehicle, @NonNull final  String userId,
                        @NonNull final BookingMode mode) {
        return bookingManagerService.book(vehicle, userId, mode);
    }

    public void cancel(@NonNull Booking booking) {
        bookingManagerService.cancel(booking);
    }

    public Invoice returnVehicle(@NonNull Booking booking) {
        bookingManagerService.cancel(booking);
        return invoiceGenerationService.apply(booking);

    }
}
