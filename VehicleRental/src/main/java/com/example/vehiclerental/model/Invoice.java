package com.example.vehiclerental.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class Invoice {
    private final Booking booking;
    private final Double amount;
}
