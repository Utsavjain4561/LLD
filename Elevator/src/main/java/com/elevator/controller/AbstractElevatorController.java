package com.elevator.controller;

import com.elevator.enums.ButtonStateEnum;
import com.elevator.model.ElevatorButton;
import lombok.NonNull;
import lombok.val;

public abstract class AbstractElevatorController {

    void toggleButton(@NonNull final ElevatorButton<?> elevatorButton) {
        val newButtonState = elevatorButton.getButtonState().equals(ButtonStateEnum.PRESSED) ?
                ButtonStateEnum.NOT_PRESSED : ButtonStateEnum.PRESSED;
        elevatorButton.toBuilder().buttonState(newButtonState);
    }

}
