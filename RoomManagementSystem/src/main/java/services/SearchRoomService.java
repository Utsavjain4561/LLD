package services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Building;
import models.Floor;
import models.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class SearchRoomService {

    private final AvailabilityService availabilityService;
    public List<Room> listRooms(@NonNull final String userId, @NonNull final Building building) {

        val rooms = building.getFloors().values()
                .stream().map(this::listRooms)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("UserId: {} Search rooms result: {}", userId, rooms);
        return rooms;
    }

    public List<Room> listRooms(@NonNull final String userId, @NonNull final Building building,
                                         @NonNull final Integer startTime, @NonNull final Integer endTime) {
        val availableRooms = listRooms(userId, building).stream()
                .filter(room -> availabilityService.isAvailable(room.getId(), startTime, endTime))
                .collect(Collectors.toList());
        log.info("UserId: {} Available rooms result: {}", userId, availableRooms);
        return availableRooms;
    }

    private List<Room> listRooms(@NonNull final Floor floor){
        return new ArrayList<>(floor.getRooms().values());
    }
}
