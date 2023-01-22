package functions;

import lombok.val;
import models.Building;
import models.Floor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;

public class AddFloor implements Function<Pair<Building, Integer>, Floor> {
    @Override
    public Floor apply(Pair<Building, Integer> buildingIntegerPair) {
        val building = buildingIntegerPair.getLeft();
        val floorNo = buildingIntegerPair.getRight();
        val floor = new Floor(floorNo);
        building.addFloor(floor);
        return floor;
    }
}
