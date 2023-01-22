package com.elevator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FloorEnum {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);
    private final Integer floorNumber;
}
