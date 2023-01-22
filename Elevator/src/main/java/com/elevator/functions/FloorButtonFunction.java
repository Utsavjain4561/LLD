package com.elevator.functions;

import com.elevator.enums.ButtonStateEnum;
import com.elevator.enums.ButtonTypeEnum;
import com.elevator.enums.FloorEnum;
import com.elevator.model.ElevatorButton;
import com.elevator.model.NumberButton;
import lombok.val;

import java.util.function.Function;

public class FloorButtonFunction implements Function<FloorEnum, ElevatorButton<NumberButton>> {
    @Override
    public ElevatorButton<NumberButton> apply(FloorEnum floorEnum) {
        val numberButton = new NumberButton(floorEnum, ButtonTypeEnum.FLOOR_BUTTON);
        return new ElevatorButton<>(numberButton,  ButtonStateEnum.NOT_PRESSED);
    }
}
