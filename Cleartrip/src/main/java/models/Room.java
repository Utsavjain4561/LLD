package models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@ToString
public class Room {
    private final String id = UUID.randomUUID().toString();
    private final String type;
    @Setter
    private Boolean available = Boolean.TRUE;
    private final Double price;


}
