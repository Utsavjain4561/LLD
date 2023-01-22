package com.example.vehiclerental.model;

import com.example.vehiclerental.enums.BookingMode;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder(toBuilder = true)
@Getter
public class Booking {
    private final String bookingId;
    private final String userId;
    private final SelectedVehicle selectedVehicle;
    private final Instant pickDate;
    @Builder.Default
    private final String pickup = "New Agra flyover";
    private final Instant bookingDate;
    private final BookingMode bookingMode;
}
