package functions;

import models.Building;

import java.util.function.Function;

public class AddBuilding implements Function<String, Building> {
    @Override
    public Building apply(String buildingId) {
        return new Building(buildingId);
    }
}
