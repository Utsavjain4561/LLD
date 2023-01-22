import com.elevator.controller.ElevatorController;
import com.elevator.controller.FloorElevatorController;
import com.elevator.enums.FloorEnum;
import com.elevator.functions.ElevatorButtonFunction;
import com.elevator.functions.FloorButtonFunction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
public class Main {
    private static final FloorButtonFunction floorButtonFunction = new FloorButtonFunction();
    private static final ElevatorButtonFunction elevatorButtonFunction = new ElevatorButtonFunction();

    public static void main(String[] args) {
        val floorList = Arrays.asList(FloorEnum.values());
        val floorButtons = floorList.stream().map(floorButtonFunction).collect(Collectors.toList());
        val elevatorButtons = floorList.stream().map(elevatorButtonFunction).collect(Collectors.toList());
        val floorElevatorControllers = IntStream.range(0, floorList.size())
                .mapToObj(index -> new FloorElevatorController(floorButtons.get(index), floorList.get(index)))
                .collect(Collectors.toList());
        val elevatorController = ElevatorController.getController();
        // Call the elevator
        floorElevatorControllers.get(1).call();
        floorElevatorControllers.get(0).call();
        floorElevatorControllers.get(2).call();
        floorElevatorControllers.get(3).call();

        // go to 1st floor
        elevatorController.goToFloor(elevatorButtons.get(0));
        elevatorController.open();
        elevatorController.close();

        //elevatorController.goToFloor(floorButtons.get(0));
    }
}
