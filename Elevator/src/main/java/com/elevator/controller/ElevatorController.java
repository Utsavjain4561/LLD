package com.elevator.controller;

import com.elevator.enums.ButtonTypeEnum;
import com.elevator.enums.DoorButtonEnum;
import com.elevator.functions.DoorButtonFunction;
import com.elevator.model.CharacterButton;
import com.elevator.model.Elevator;
import com.elevator.model.ElevatorButton;
import com.elevator.model.NumberButton;
import lombok.NonNull;
import lombok.val;

import java.util.Objects;


public class ElevatorController extends AbstractElevatorController implements IElevatorControl{
    private static ElevatorController elevatorController;
    private final ElevatorButton<CharacterButton> openButton;
    private final ElevatorButton<CharacterButton> closeButton;

    private ElevatorController(){
        val doorButtonFunction = new DoorButtonFunction();
        this.openButton = doorButtonFunction.apply(DoorButtonEnum.OPEN);
        this.closeButton = doorButtonFunction.apply(DoorButtonEnum.CLOSE);
    }

    public static ElevatorController getController(){
        if(Objects.isNull(elevatorController)){
            elevatorController = new ElevatorController();
        }
        return elevatorController;
    }

    @Override
    public void goToFloor(@NonNull ElevatorButton<NumberButton> elevatorButton) {
        super.toggleButton(elevatorButton);
        val button = elevatorButton.getButton();
        if (button.getButtonType().equals(ButtonTypeEnum.ELEVATOR_BUTTON)) {
            Elevator.getElevator().update(button.getFloor());
        } else {
            throw new IllegalArgumentException("Pleas press a floor button!!");
        }
        super.toggleButton(elevatorButton);
    }

    @Override
    public void open() {
        super.toggleButton(openButton);
        Elevator.getElevator().updateState(DoorButtonEnum.OPEN);
        super.toggleButton(openButton);

    }

    @Override
    public void close() {
        super.toggleButton(closeButton);
        Elevator.getElevator().updateState(DoorButtonEnum.CLOSE);
        super.toggleButton(closeButton);

    }
}
