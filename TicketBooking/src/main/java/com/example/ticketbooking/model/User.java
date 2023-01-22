package com.example.ticketbooking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class User {
    private final String username;
    private final String email;
    private final Integer age;
}
