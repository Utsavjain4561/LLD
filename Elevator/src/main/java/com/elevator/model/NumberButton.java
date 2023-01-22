package com.elevator.model;

import com.elevator.enums.ButtonTypeEnum;
import com.elevator.enums.FloorEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class NumberButton {
    private final FloorEnum floor;
    private final ButtonTypeEnum buttonType;

}
