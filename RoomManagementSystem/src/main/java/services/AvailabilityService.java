package services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import models.Booking;

import java.util.Map;

@RequiredArgsConstructor
public class AvailabilityService {

    public final Map<String, Booking> bookings;

    private Boolean isNotOverlapping(final Integer startTime, final Integer endTime,
                                     final Integer bookStartTime, final Integer bookEndTime) {
        return endTime < bookStartTime || bookEndTime < startTime;
    }

    public Boolean isAvailable(@NonNull final String roomId, @NonNull final Integer startTime, @NonNull Integer endTime) {
        if(!bookings.containsKey(roomId)) return Boolean.TRUE;
        val booking  = bookings.get(roomId);
        return isNotOverlapping(startTime, endTime, booking.getStartTime(), booking.getEndTime());
    }
}
