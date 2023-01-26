package services;

import dao.Storage;
import enums.UserType;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.Hotel;
import models.Room;
import models.User;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
public class InventoryManagerService {

    private final Map<User, List<Hotel>> properties = Storage.getProperties();

    public void addInventory(final User user, final String hotelName, final String roomType, final Double price,
                             final Integer roomsAvailable) {
        val hotel = Storage.getHotels().get(hotelName);
        if (user.getType().equals(UserType.ADMIN) && properties.get(user).contains(hotel)) {
            IntStream.range(1, roomsAvailable + 1).boxed().map(key -> new Room(roomType, price))
                    .forEach(room -> {
                        log.info("Room: {} added to hotel :{} ", room.getType(), hotel.getTitle());
                        hotel.addRoom(room);
                    });
            log.info("Price and inventory added successfully");

        } else {
            throw new UnsupportedOperationException("User is not allowed to add inventory to hotel");
        }
    }
}
