package models;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class Floor {
    private final Integer number;
    @Setter
    private Map<String, Room> rooms = new HashMap<>();

    public void addRoom(@NonNull final Room room) {
        rooms.putIfAbsent(room.getId(), room);
    }
}
