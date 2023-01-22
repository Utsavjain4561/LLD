package com.elevator.functions;

import com.elevator.enums.ButtonStateEnum;
import com.elevator.enums.ButtonTypeEnum;
import com.elevator.enums.DoorButtonEnum;
import com.elevator.model.CharacterButton;
import com.elevator.model.ElevatorButton;
import lombok.val;

import java.util.function.Function;

public class DoorButtonFunction implements Function<DoorButtonEnum, ElevatorButton<CharacterButton>> {
    @Override
    public ElevatorButton<CharacterButton> apply(DoorButtonEnum doorButtonEnum) {
        val button  = new CharacterButton(doorButtonEnum.getSymbol(),  ButtonTypeEnum.ELEVATOR_BUTTON);
        return new ElevatorButton<>(button, ButtonStateEnum.NOT_PRESSED);
    }
}
