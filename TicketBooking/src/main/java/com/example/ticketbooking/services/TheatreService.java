package com.example.ticketbooking.services;

import com.example.ticketbooking.model.Show;
import com.example.ticketbooking.model.Theatre;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TheatreService {
    private final Theatre theatre;

    public List<Show> getShows() {
        return theatre.getShows();
    }
}
