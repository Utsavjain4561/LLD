package models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@ToString
@Getter
public class Booking {
    private final String id = UUID.randomUUID().toString();
    private final Date checkInDate;
    private final Date checkOutDate;
    private final User user;
    private final Map<String, Room> roomsBooked;

    public void addRoom(@NonNull final Room room){
        roomsBooked.putIfAbsent(room.getId(), room);
    }
}
