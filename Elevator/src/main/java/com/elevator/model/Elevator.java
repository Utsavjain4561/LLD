package com.elevator.model;

import com.elevator.enums.DoorButtonEnum;
import com.elevator.enums.FloorEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Objects;

import static com.elevator.utils.LogUtils.logCurrentElevatorState;

public class Elevator implements Serializable {
    @Getter
    private FloorEnum floor;
    @Getter
    @Builder.Default
    private DoorButtonEnum elevatorState = DoorButtonEnum.CLOSE;
    private static Elevator elevator;

    private Elevator(FloorEnum floor) {
        this.floor = floor;
    }
    private Elevator(){}

    public static Elevator getElevator(){
        if(Objects.isNull(elevator)){
            elevator = new Elevator();
        }
        return elevator;
    }

    public void update(@NonNull final FloorEnum floor){
        this.floor = floor;
        logCurrentElevatorState();
    }
    public void updateState(@NonNull final DoorButtonEnum state){
        this.elevatorState = state;
        logCurrentElevatorState();
    }
}
