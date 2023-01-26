package models;

import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@ToString
@Getter
public class Hotel {
    private final String title;
    private final String city;
    private final Integer rating;
    private final Set<String> roomTypes;
    private final Map<String, Room> rooms = new HashMap<>();
    private final User user;
    @Setter
    private Boolean roomsAvailable = Boolean.TRUE;

    public void addRoom(@NonNull final Room room){
        rooms.putIfAbsent(room.getId(), room);
    }
}
