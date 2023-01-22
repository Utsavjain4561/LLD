package com.example.ticketbooking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Theatre {
    private final List<Show> shows = new ArrayList<>();
    private final String name;

    public void addShow(final Show show) {
        this.shows.add(show);
    }


}
