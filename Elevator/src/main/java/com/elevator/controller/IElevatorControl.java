package com.elevator.controller;

import com.elevator.model.ElevatorButton;
import com.elevator.model.NumberButton;
import lombok.NonNull;

public interface IElevatorControl {
    void goToFloor(@NonNull final ElevatorButton<NumberButton> elevatorButton);
    void open();
    void close();
}
