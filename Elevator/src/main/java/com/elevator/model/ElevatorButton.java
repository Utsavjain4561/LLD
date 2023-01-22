package com.elevator.model;

import com.elevator.enums.ButtonStateEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class ElevatorButton<T> {
    private final T button;
    private final ButtonStateEnum buttonState;

}
