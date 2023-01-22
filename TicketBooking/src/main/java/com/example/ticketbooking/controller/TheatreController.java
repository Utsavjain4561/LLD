package com.example.ticketbooking.controller;

import com.example.ticketbooking.model.Show;
import com.example.ticketbooking.services.TheatreService;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class TheatreController {
    private final TheatreService theatreService;
    public List<Show> getShows(){
        return theatreService.getShows();
    }
}
