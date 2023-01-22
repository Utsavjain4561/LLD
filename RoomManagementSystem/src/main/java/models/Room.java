package models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Room {
    private final String id;
    private final Integer floorNo;
    private final String buildingId;
}
