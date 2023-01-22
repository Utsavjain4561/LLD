package com.example.ticketbooking.controller;

import com.example.ticketbooking.exceptions.RecoverableException;
import com.example.ticketbooking.model.BookingSession;
import com.example.ticketbooking.model.BookingStateEnum;
import com.example.ticketbooking.model.Ticket;
import com.example.ticketbooking.services.IPaymentService;
import com.example.ticketbooking.services.SeatDeselectorService;
import com.example.ticketbooking.services.TicketGenerationService;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final IPaymentService paymentService;
    private final SeatDeselectorService seatDeselectorService;
    private final TicketGenerationService ticketGenerationService;
    private final PaymentControllerProps paymentControllerProps;

    public Ticket pay(@NonNull final BookingSession session) {
        var retry = paymentControllerProps.getRetry();
        Ticket ticket = null;
        boolean status;
        try {
            do {
                val bookingSession = paymentService.pay(session);
                status = bookingSession.getStatus().equals(BookingStateEnum.BOOKED);
                if (status) {
                    ticket = ticketGenerationService.generateTicket(bookingSession);
                    break;
                } else {
                    log.warn("Payment unsuccessful retrying in 5 sec");
                    Thread.sleep(1000);
                }
            } while (retry-- != 0);

            if (!status) {
                seatDeselectorService.unlockSeats(session.getSelectedSeats());
                throw new RecoverableException("Payment failed due to internal server error");
            }
        } catch (Exception e) {
            log.error("Recoverable exception : {}", e.getMessage());
        }
        return ticket;
    }


    @Builder(toBuilder = true)
    @Getter
    public static class PaymentControllerProps {
        @Builder.Default
        private Integer retry = 0;
    }
}
