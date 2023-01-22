package com.elevator.utils;

import com.elevator.model.Elevator;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class LogUtils {
    public static void logCurrentElevatorState(){
        val elevator = Elevator.getElevator();
        log.info("Elevator is at floor: {} and is: {} ", elevator.getFloor(), elevator.getElevatorState());
    }
}
