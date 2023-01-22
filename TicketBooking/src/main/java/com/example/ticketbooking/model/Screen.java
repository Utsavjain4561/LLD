package com.example.ticketbooking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Screen {
    private final Movie movie;
    private final String audiName;
}
