package models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Booking {

    private final String id = UUID.randomUUID().toString();
    private final String userId;
    private final Integer startTime;
    private final Integer endTime;
    private final Room bookedRoom;
}
