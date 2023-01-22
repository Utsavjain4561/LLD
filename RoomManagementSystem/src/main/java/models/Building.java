package models;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class Building {
    private final String id;
    @Setter
    private Map<Integer, Floor> floors = new HashMap<>();

    public void addFloor(@NonNull final Floor floor) {
        floors.putIfAbsent(floor.getNumber(), floor);
    }

}
