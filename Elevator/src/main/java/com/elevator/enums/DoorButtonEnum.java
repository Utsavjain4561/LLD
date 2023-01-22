package com.elevator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DoorButtonEnum {
    CLOSE('C'),
    OPEN('O');
    private final Character symbol;
}
