package com.example.vehiclerental.services;

import com.example.vehiclerental.model.Booking;
import com.example.vehiclerental.model.Invoice;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
public class InvoiceGenerationService implements Function<Booking, Invoice> {
    private final Integer FIXED_RATE_PER_DAY = 2000;

    @Override
    public Invoice apply(Booking booking) {
        val currentTme = Instant.now().toEpochMilli();
        val bookingTime = booking.getBookingDate().toEpochMilli();
        val diff = currentTme - bookingTime;
        log.debug("Millis between : {}", diff);
        val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        val amount = 1.0 * diff * FIXED_RATE_PER_DAY;
        return Invoice.builder().booking(booking).amount(amount).build();
    }
}
