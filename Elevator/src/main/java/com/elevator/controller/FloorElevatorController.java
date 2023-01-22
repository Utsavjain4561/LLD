package com.elevator.controller;

import com.elevator.enums.FloorEnum;
import com.elevator.model.Elevator;
import com.elevator.model.ElevatorButton;
import com.elevator.model.NumberButton;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FloorElevatorController extends AbstractElevatorController implements IFloorControl {
    private final ElevatorButton<NumberButton> floorButton;
    private final FloorEnum floorNumber;

    @Override
    public void call() {
        super.toggleButton(floorButton);
        Elevator.getElevator().update(floorNumber);
        super.toggleButton(floorButton);
    }
}
