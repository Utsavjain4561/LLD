package com.example.ticketbooking;

import com.example.ticketbooking.controller.BookingController;
import com.example.ticketbooking.controller.PaymentController;
import com.example.ticketbooking.controller.TheatreController;
import com.example.ticketbooking.handler.OverlappingSeatsHandler;
import com.example.ticketbooking.model.Movie;
import com.example.ticketbooking.model.Screen;
import com.example.ticketbooking.model.Seat;
import com.example.ticketbooking.model.Show;
import com.example.ticketbooking.model.Theatre;
import com.example.ticketbooking.model.User;
import com.example.ticketbooking.services.FaultyPaymentServiceImp;
import com.example.ticketbooking.services.SeatAvailabilityService;
import com.example.ticketbooking.services.SeatDeselectorService;
import com.example.ticketbooking.services.SeatSelectorService;
import com.example.ticketbooking.services.TheatreService;
import com.example.ticketbooking.services.TicketGenerationService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class TicketBookingApplicationPaymentFailureTest {
    private static final Long SECONDS_IN_A_DAY = 86400L;
    private static final Long MOVIE_DURATION_IN_SECONDS = 7200L;
    private static final Instant MOVIE_FIRST_SHOW_START_TIME = Instant.now().plusSeconds(SECONDS_IN_A_DAY);
    private static final Instant MOVIE_FIRST_SHOW_END_TIME = MOVIE_FIRST_SHOW_START_TIME.plusSeconds(MOVIE_DURATION_IN_SECONDS);
    private BookingController bookingController;
    private PaymentController paymentController;

    @BeforeEach
    void setup() {
        val seatAvailabilityService = new SeatAvailabilityService();
        val overlappingSeatsHandler = new OverlappingSeatsHandler();
        val seatSelectorService = new SeatSelectorService(overlappingSeatsHandler);
        val seatDeselectorService = new SeatDeselectorService();
        val ticketGenerationService = new TicketGenerationService();
        val paymentService = new FaultyPaymentServiceImp();
        this.paymentController = new PaymentController(paymentService, seatDeselectorService, ticketGenerationService,
                PaymentController.PaymentControllerProps.builder().retry(3).build());
        this.bookingController = new BookingController(seatAvailabilityService, seatSelectorService);

    }

    @Test
    void testWhenThereIsPaymentError() throws Exception {
        // create test data
        val user1 = new User("Utsav", "utsavslife@gmail.com", 25);
        val user2 = new User("Vinayak", "vinayakjain@outlook.com", 20);
        val movie1 = new Movie("Interstellar");
        val seats = generateSeats();
        val screen1 = new Screen(movie1, "AUDI1");
        val show1_1 = new Show(movie1, screen1, MOVIE_FIRST_SHOW_START_TIME, MOVIE_FIRST_SHOW_END_TIME, seats);
        val theatre = new Theatre("Omaxe");
        theatre.addShow(show1_1);
        val theatreService = new TheatreService(theatre);
        val theatreController  = new TheatreController(theatreService);
        val currentShows = theatreController.getShows();
        Assertions.assertEquals(show1_1, currentShows.get(0));

        CompletableFuture.supplyAsync(() ->{
            var bookingSession1 = bookingController.bookShow(user1, show1_1);
            val availableSeats1 = bookingSession1.getAvailableSeats();
            val seatsToBook1 = List.of(availableSeats1.get(0), availableSeats1.get(1), availableSeats1.get(2));
            bookingSession1 = bookingController.bookSeats(bookingSession1, seatsToBook1);
            return paymentController.pay(bookingSession1);
        }).thenAccept(ticket -> Assert.isNull(ticket, "Ticket is Null"));

        Thread.sleep(1000);
        var bookingSession2 = bookingController.bookShow(user2, show1_1);
        var availableSeats2 = bookingSession2.getAvailableSeats();
        Assertions.assertEquals(availableSeats2.size(), 2);
        Assertions.assertEquals(availableSeats2.get(0), seats.get(3));
        Assertions.assertEquals(availableSeats2.get(1), seats.get(4));
        Thread.sleep(5000);
        bookingSession2 = bookingController.bookShow(user2, show1_1);
        availableSeats2 = bookingSession2.getAvailableSeats();
        Assertions.assertEquals(availableSeats2.size(), 5);
    }

    private List<Seat> generateSeats() {
        return IntStream.range(1, 6).boxed().map(index -> Seat.builder().seatNo(index).build()).toList();
    }
}
